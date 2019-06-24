# 英文
```
Parameter   參數
Attribute   屬性
Require     要求
Request     請求
Dispatcher  調度
forward     前進
Writer      作家
response    響應
Match       匹配
Pattern     格局
compile     編輯
Stream      流
```

#網址
Optional方法
>http://blog.tonycube.com/2015/10/java-java8-4-optional.html

# 方法
### 正規表示法
```
設置條件
Pattern pattern=Pattern.compile("filename=\"(.*)\"");

設置範圍
Matcher matcher=pattern.matcher(contentTxt);

執行
matcher.find();

取得內容
matcher.group(1);

取得位置索引
matcher.start(1);
```
### 網站資訊
```
取得整個路徑
request.getRequestURI()

取得環境路徑
request.getContextPath()

取得servlet路徑
request.getServletPath()

取得classes路徑
request.getPathInfo()

取得列舉值
mapping.getMappingMatch()

取得實際符合對比值
mapping.getMatchValue()

取得servlet設定值
mapping.getPattern()

取得指定標頭字串
getHeader()

取得所有標頭名稱
getHeaderNames()
```
### 抓取資料
```
取得串流
getInputStream()

取得檔案類型
getContentType()

取得緩衝區(BufferedReader)
getReader()

對客戶端進行輸出
getWriter()

```
### 字串
```
抓取字串
substring(初始位置,結束位置)

取得查詢字串索引位置
lastIndexOf('=')

取得字串索引位置
indexOf(查詢字串,查詢起始位置)
```
### Map
```
宣告map變數
private Map<String, String> messages

建立物件
messages = new HashMap<>();

新增資料
messages.put(變數, 值);
 
取得值
messages.get(變數)
```
### 上傳資料
```
取得檔案
getPart("input name")

取得檔案名稱
getSubmittedFileName

取得多個檔案
getParts()

過濾器
filter()
```
### 調派請求
```
取得調派對象
getRequestDispatcher()

RequestDispatcher有兩個方法:

轉發，取得調派對象訊息
forward()

包含，取得調派對象訊息也保留本身訊息
include()
```
### 其他
```
讀取資料
reader.readLine()

編碼設定
String contentAsTxt = new String(content, "ISO-8859-1");

設定緩衝
byte[] buffer= new byte[1024];

取得緩衝長度
length=in.read(buffer)

轉換位元陣列
toByteArray()

取得長度
length()

寫入資料
write()

設定編碼
setCharacterEncoding("UTF-8");

設定內容型態
setContentType("text/html; charset=UTF-8");

設定狀態碼
setStatus

狀態碼分別為:
正常回應 200
HttpServletResponse.SC_OK

重新導向
永久轉移 301
HttpServletResponse.SC_MOVED_PERMANENTLY

暫時轉移 302
HttpServletResponse.SC_FOUND(建議使用)
HttpServletResponse.SC_MOVED_TEMPORARILY

重新請求URL
sendRedirect(url)

回應404
sendError(HttpServletResponse.SC_NOT_FOUND, "自定義內容");

設定地區
setLocale(new Locale("zh", "TW"));

將陣列轉至List
asList(array);

判斷值是否存在
isPresent();

設置屬性
setAttribute(name,value);

取得屬性
getAttribute("name")

生成有順序的字串，結束值為獨佔
range(1,5) //1,2,3,4

生成有順序的字串，結束值為包含
rangeClosed(1,5) //1,2,3,4,5

如果用會關閉cookie，它會以適當的格式將jsessionid附加到URL以被識別為會話標識符。
encodeURL()
```

### 增強式for迴圈
```
第一種
Collections.list(request.getParameterNames()) //將資料名稱加入list
           .stream()                          //加入串流
           .map(request::getParameter)        //將資料帶入map
           .forEach(out::println);            //將資料全部印出

第二種
將標頭存成list,使用foreach讀出來
 Collections.list(request.getHeaderNames())
                .forEach(name -> {
                    out.printf("%s: %s<br>%n", name, request.getHeader(name));
                });           

```
###  建立驗證碼
```
轉換值的型態，範例為轉乘String
mapToObj(String::valueOf)

將取到的資料組合起來
collect(Collectors.joining())

建立驗證碼圖片
ImageIO.write(
        passwordImage(passwd),      //設定圖片樣式
        "JPG",                      //設定副檔名
        response.getOutputStream()  //取得串流
);

 private BufferedImage passwordImage(String password) throws IOException{
        //建立圖片緩存
        BufferedImage bufferedImage=new BufferedImage(60,20,BufferedImage.TYPE_INT_RGB);
        //建立畫布
        Graphics g=bufferedImage.getGraphics();
        //設定顏色
        g.setColor(Color.WHITE);
        //設定字型樣式
        g.setFont(new Font("標楷體",Font.BOLD,16));
        //寫入(畫)字串
        g.drawString(password,10,15);
        return bufferedImage;
 }
```
### Cookie設置
```
Cookie cookie = new Cookie("user", "leelo"); //建立物件
cookie.setMaxAge(7 * 24 * 60 * 60);          //設定存活時間
response.addCookie(cookie);                  加入cookie
```
### Cookie取得
```
Optional<Cookie> userCookic=Optional.ofNullable(request.getCookies())   //取得cookie，接受空值，並轉為optional
                                            .flatMap(this::userCookie); //將取得的所有optional合併成一個stream
 private Optional<Cookie> userCookie(Cookie[] cookies){
         return Stream.of(cookies)                                                                          //取得串流
                 .filter(cookie -> "user".equals(cookie.getName()) && "leelo".equals((cookie.getValue())))  //判斷cookie
                 .findFirst();                                                                              //取得串流的第一個項目
     }
```
### Session
```
建立Session
getSession().setAttribute("name", value); 

取得Session
getSession().getAttribute("name", value); 

清除Session
getSession().invalidate();
```