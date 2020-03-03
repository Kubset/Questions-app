# Questions-app


###Email configuration
for @gmail account please remember to turn off two step verification, 
enable access by less secure apps and unlock captcha if necessary.
https://myaccount.google.com/lesssecureapps
https://accounts.google.com/b/0/DisplayUnlockCaptcha


## Api documentation

### Categories
##### Create category 
Endpoint for create new category

 ```@POST {api_endpoint}/category```
 
 Example body: 
 
 ```
{
    "name": "Databases",
    "superCategory": {
        "name": "AWS"
    }
}
```

##### get all categories(json format)
Endpoint gives possibility to get category tree from specified category

Endpoint for get category tree:

 ```@GET {api_endpoint}/category/{name}```
 
parameter | description
------|-----
name | name of root category (to display everything insert 'root'

 
 ##### get all categories with questions(string format)
 Endpoint gives possibility to get category tree with questions
 
 Endpoint for get category tree:
 
  ```@GET {api_endpoint}/category/tree/{name}```
  
 parameter | description
 ------|-----
 name | name of root category (to display everything insert 'root'
 
### Questions

##### Create question
Endpoint for create new question

 ```@POST {api_endpoint}/question```
 
 Example body (max_points, points are unused for now): 
 
 ```
{
	"question": "example question",
	"max_points": 0,
	"points": 0,
	"category": {
		"name": "example database"
	}

}
```

