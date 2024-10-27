---
title: proxy-text
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.23"

---

# proxy-text

Base URLs:

# Authentication

# Default

## POST 授权示例

POST /proxy/api/hello/violet

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userid|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

## POST 上传文件

POST /proxy/api/file/upload

> Body 请求参数

```yaml
file: file://C:\Users\COLORFUL\Pictures\Screenshot 2024-08-24 224241.png

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userid|header|string| 否 |none|
|body|body|object| 否 |none|
|» file|body|string(binary)| 否 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

## GET 下载文件

GET /proxy/api/file/download/Screenshot 2024-08-24 224241.png

> Body 请求参数

```yaml
{}

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userid|header|string| 否 |none|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

# 数据模型

