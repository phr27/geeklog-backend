# Core Api of Geeklog

## User

### User Login

- [X] __1__ POST /login

req:

```json
{
    "username": "syf",
    "password": "123456"
}
```

res:

```json
{
    "code": 200,
    "message": "Success",
    "data": {
        "token": "1j312lnn21312"
    }
}
```

- [X] __2__ GET /users/:user_id

res:

```json
{
    "code": 200,
    "message": "Success",
    "data": {
        "user_id": 1,
        "username": "loginname",
        "nickname": "a-nick-name",
        "bio": "some info about me",
        "avatar": "http://....",
        "is_admin": false,
        "can_comment": true,
        "can_write_article": true
    }
}
```

- [ ] __3__ POST /users (注册)

req:

```json
{
    "username": "syf",
    "password": "123456",
    "nickname": "rabbit-a512",
    "bio": "some info about me"
}
```

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "username": "loginname",
        "nickname": "a-nick-name",
        "avatar": "指向默认图片的路径",
        "bio": "some info about me",
        "is_admin": false,
        "can_comment": true,
        "can_write_article": true
    }
}
```

- [ ] __4__ PUT /users/:user_id (头像路径单独维护)

req:

```json
{
    "nickname": "another",
    "bio": "another bio"
}
```

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "username": "loginname",
        "nickname": "a-nick-name",
        "avatar": "指向默认图片的路径",
        "bio": "some info about me",
        "is_admin": false,
        "can_comment": true,
        "can_write_article": true
    }
}
```

### 修改用户密码

- [ ] __5__ POST /change-password

req:

```json
{
    "user_id": 1,
    "old_password": "123456",
    "new_password": "234567"
}
```

## Article

- [ ] __6__ GET /articles/hot/:count (根据点赞数＋收藏数＋评论数排序，获取最热文章)

res:

```json
{
    "code": 200,
    "message": "success",
    "data": [
        {
            "article_id": 1,
            "title": "a title",
            "created_at": 12211033,
            "modified_at": 16125652,
            "content": "some content",
            "user_id": 1,
            "category_id": 2,
            "tags": "java,python,sql",
            "display": true,
            "collect_count": 22,
            "star_count": 23,
            "comment_count": 320
        },
        {
            "article_id": 2,
            "title": "a title",
            "created_at": 12211033,
            "modified_at": 16125652,
            "content": "some content",
            "user_id": 1,
            "category_id": 2,
            "tags": "java,python,sql",
            "display": true,
            "collect_count": 22,
            "star_count": 23,
            "comment_count": 320
        }
    ]
}
```

- [ ] __7__ GET /articles?page=1&size=30&category_id=1

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "total": 234,
        "articles": [
            {
                "article_id": 1,
                "title": "a title",
                "created_at": 12211033,
                "modified_at": 16125652,
                "content": "some content",
                "user_id": 1,
                "category_id": 2,
                "tags": "java,python,sql",
                "display": true,
                "collect_count": 22,
                "star_count": 23,
                "comment_count": 320
            },
            {
                "article_id": 2,
                "title": "a title",
                "created_at": 12211033,
                "modified_at": 16125652,
                "content": "some content",
                "user_id": 1,
                "category_id": 2,
                "tags": "java,python,sql",
                "display": true,
                "collect_count": 22,
                "star_count": 23,
                "comment_count": 320
            }
        ]
    }
}
```

- [ ] __8__ GET /articles/:article_id

res:

```json
{
    "code": 200,
    "message": "success".
    "data": {
        "article_id": 2,
        "title": "a title",
        "created_at": 12211033,
        "modified_at": 16125652,
        "content": "some content",
        "user_id": 1,
        "category_id": 2,
        "tags": "java,python,sql",
        "display": true,
        "collect_count": 22,
        "star_count": 23,
        "comment_count": 320
    }
}
```

- [ ] __9__ POST /articles

req:

```json
{
    "title": "a title",
    "content": "more content",
    "user_id": 2,
    "category_id": 2,
    "tags": "vue,typescript,webpack"
}
```

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "article_id": 1,
        "title": "a title",
        "created_at": 12211033,
        "modified_at": 16125652,
        "content": "some content",
        "user_id": 1,
        "category_id": 2,
        "tags": "java,python,sql",
        "display": true,
        "collect_count": 22,
        "star_count": 23,
        "comment_count": 320
    }
}
```

- [ ] __10__ PUT /articles/:article_id

req:

```json
{
    "title": "a title",
    "content": "more content",
    "category_id": 2,
    "tags": "vue,typescript,webpack"
}
```

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "article_id": 1,
        "title": "a title",
        "created_at": 12211033,
        "modified_at": 16125652,
        "content": "some content",
        "user_id": 1,
        "category_id": 2,
        "tags": "java,python,sql",
        "display": true,
        "collect_count": 22,
        "star_count": 23,
        "comment_count": 320
    }
}
```

- [ ] __11__ DELETE /articles/:article_id

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "article_id": 1,
        "title": "a title",
        "created_at": 12211033,
        "modified_at": 16125652,
        "content": "some content",
        "user_id": 1,
        "category_id": 2,
        "tags": "java,python,sql",
        "display": true,
        "collect_count": 22,
        "star_count": 23,
        "comment_count": 320
    }
}
```

## Comment

- [ ] __12__ GET /comments/latest/:count

res:

```json
{
    "code": 200,
    "message": "success",
    "data": [
        {
            "comment_id": 1,
            "user_id": 2,
            "content": "blala",
            "parent_id": 1,
            "root_id": 2,
            "created_at": 13512312331,
            "article_id": 2
        },
        {
            "comment_id": 2,
            "user_id": 2,
            "content": "blala",
            "parent_id": 1,
            "root_id": 2,
            "created_at": 13512312331,
            "article_id": 2
        }
    ]
}
```

- [ ] __13__ POST /comments

req:

```json
{
    "user_id": 1,
    "article_id": 2, (一级评论需要提供)
    "content": "some content",
    "parent_id": null,
}
```

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "comment_id": 1,
        "user_id": 1,
        "article_id": 2,
        "content": "some content",
        "parent_id": null,
        "root_id": null
    }
}
```

## 涉及多表的API

### 根据user_id, 获取他写的文章

- [ ] __14__ GET /users/:user_id/write/articles

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "total": 234,
        "articles": [
            {
                "article_id": 1,
                "title": "a title",
                "created_at": 12211033,
                "modified_at": 16125652,
                "content": "some content",
                "user_id": 1,
                "category_id": 2,
                "tags": "java,python,sql",
                "display": true,
                "collect_count": 22,
                "star_count": 23,
                "comment_count": 320
            },
            {
                "article_id": 2,
                "title": "a title",
                "created_at": 12211033,
                "modified_at": 16125652,
                "content": "some content",
                "user_id": 1,
                "category_id": 2,
                "tags": "java,python,sql",
                "display": true,
                "collect_count": 22,
                "star_count": 23,
                "comment_count": 320
            }
        ]
    }
}
```

### 根据user_id, 获取他点赞的的文章

- [ ] __15__ GET /users/:user_id/star/articles

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "total": 234,
        "articles": [
            {
                "article_id": 1,
                "title": "a title",
                "created_at": 12211033,
                "modified_at": 16125652,
                "content": "some content",
                "user_id": 1,
                "category_id": 2,
                "tags": "java,python,sql",
                "display": true,
                "collect_count": 22,
                "star_count": 23,
                "comment_count": 320
            },
            {
                "article_id": 2,
                "title": "a title",
                "created_at": 12211033,
                "modified_at": 16125652,
                "content": "some content",
                "user_id": 1,
                "category_id": 2,
                "tags": "java,python,sql",
                "display": true,
                "collect_count": 22,
                "star_count": 23,
                "comment_count": 320
            }
        ]
    }
}
```

### 根据user_id, 获取他收藏的文章

- [ ] __16__ GET /users/:user_id/collect/articles

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "total": 234,
        "articles": [
            {
                "article_id": 1,
                "title": "a title",
                "created_at": 12211033,
                "modified_at": 16125652,
                "content": "some content",
                "user_id": 1,
                "category_id": 2,
                "tags": "java,python,sql",
                "display": true,
                "collect_count": 22,
                "star_count": 23,
                "comment_count": 320
            },
            {
                "article_id": 2,
                "title": "a title",
                "created_at": 12211033,
                "modified_at": 16125652,
                "content": "some content",
                "user_id": 1,
                "category_id": 2,
                "tags": "java,python,sql",
                "display": true,
                "collect_count": 22,
                "star_count": 23,
                "comment_count": 320
            }
        ]
    }
}
```

### 根据user_id, 获取他评论的文章

- [ ] __17__ GET /users/:user_id/comment/articles

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "total": 234,
        "articles": [
            {
                "article_id": 1,
                "title": "a title",
                "created_at": 12211033,
                "modified_at": 16125652,
                "content": "some content",
                "user_id": 1,
                "category_id": 2,
                "tags": "java,python,sql",
                "display": true,
                "collect_count": 22,
                "star_count": 23,
                "comment_count": 320
            },
            {
                "article_id": 2,
                "title": "a title",
                "created_at": 12211033,
                "modified_at": 16125652,
                "content": "some content",
                "user_id": 1,
                "category_id": 2,
                "tags": "java,python,sql",
                "display": true,
                "collect_count": 22,
                "star_count": 23,
                "comment_count": 320
            }
        ]
    }
}
```

### 根据article_id获取该文章的所有评论

- [ ] __18__ GET /articles/:article_id/comments (返回该文章所有一级评论)

res:

```json
{
    "code": 200,
    "message": "success",
    "data": [
        {
            "comment_id": 1,
            "user_id": 1,
            "article_id": 2,
            "content": "some content",
            "parent_id": null,
            "root_id": null
        },
        {
            "comment_id": 1,
            "user_id": 1,
            "article_id": 2,
            "content": "some content",
            "parent_id": null,
            "root_id": null
        }
    ]
}
```

- [ ] __19__ GET /comments/:comment_id/sub_comments (返回该评论的所有自评论)

res:

```json
{
    "code": 200,
    "message": "success",
    "data": [
        {
            "comment_id": 1,
            "user_id": 1,
            "article_id": 2,
            "content": "some content",
            "parent_id": 1,
            "root_id": 1
        },
        {
            "comment_id": 1,
            "user_id": 1,
            "article_id": 2,
            "content": "some content",
            "parent_id": 1,
            "root_id": 1
        }
    ]
}
```

### 根据user_id, article_id, 点赞或者取消

- [ ] __20__ POST /add-star

req:

```json
{
    "user_id": 1,
    "article_id": 1
}
```

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "star_id": 1,
        "user_id": 1,
        "article_id": 1
    }
}
```

- [ ] __21__ POST /delete-star

req:

```json
{
    "user_id": 1,
    "article_id": 1
}
```

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "star_id": 1,
        "user_id": 1,
        "article_id": 1
    }
}
```

### 根据user_id, article_id, 收藏或者取消

- [ ] __22__ POST /add-collect

req:

```json
{
    "user_id": 1,
    "article_id": 1
}
```

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "collect_id": 1,
        "user_id": 1,
        "article_id": 1,
        "created_at": 1231245255
    }
}
```

- [ ] __23__ POST /delete-collect

req:

```json
{
    "user_id": 1,
    "article_id": 1
}
```

res:

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "collect_id": 1,
        "user_id": 1,
        "article_id": 1,
        "created_at": 1234242356
    }
}
```

### 文件操作

```html
<form action="/url">
    <label for="avatar">点击上传头像</label>
    <input id="avatar" type="file" name="user_id">
    <button type="submit">上传</button>
</form>
```
