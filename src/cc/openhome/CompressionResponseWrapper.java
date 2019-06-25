package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CompressionResponseWrapper extends HttpServletResponseWrapper  {
    private GZipServletOutputStream gZipServletOutputStream;
    private PrintWriter printWriter;

    public CompressionResponseWrapper(HttpServletResponse response){
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if(printWriter != null) {
            throw new IllegalStateException();
        }
        if (gZipServletOutputStream == null) {
            gZipServletOutputStream =
                    new GZipServletOutputStream(getResponse().getOutputStream());
        }
        return gZipServletOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if(gZipServletOutputStream != null) {
            throw new IllegalStateException();
        }
        if (printWriter == null) {
            gZipServletOutputStream =
                    new GZipServletOutputStream(getResponse().getOutputStream());
            OutputStreamWriter osw =
                    new OutputStreamWriter(
                            gZipServletOutputStream, getResponse().getCharacterEncoding());
            printWriter = new PrintWriter(osw);
        }
        return printWriter;
    }

    @Override
    public void flushBuffer() throws IOException {
        if(this.printWriter != null) {
            this.printWriter.flush();
        }
        else if(this.gZipServletOutputStream != null) {
            this.gZipServletOutputStream.flush();
        }
        super.flushBuffer();
    }

    public void finish() throws IOException {
        if(this.printWriter != null) {
            this.printWriter.close();
        }
        else if(this.gZipServletOutputStream != null) {
            this.gZipServletOutputStream.finish();
        }
    }

    @Override
    public void setContentLength(int len) {}

    @Override
    public void setContentLengthLong(long length) {}
}
