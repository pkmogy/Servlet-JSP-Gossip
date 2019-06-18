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

#方法
###正規表示法
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
###網站資訊
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
###抓取資料
```
取得串流
getInputStream()

取得檔案類型
getContentType()
```
###字串
```
抓取字串
substring(初始位置,結束位置)

取得查詢字串索引位置
lastIndexOf('=')

取得字串索引位置
indexOf(查詢字串,查詢起始位置)
```
###Map
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
###其他
```
取得緩衝區(BufferedReader)
getReader()

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

對客戶端進行輸出
getWriter()
```

###增強式for迴圈
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