# Hotel



## Database

Give database name as **hotels** in MySQL


### Entity
1. Hotel:
* id - Primary Key.
* name - Hotel name.
* city - City of hotels.
* date(YYYY-MM-DD) - To search according to date.
* rooms(Int) - Room count default 0.
* stars(float) - Rating from 0 to 10
* availability(Y/N) - Y: yes, N:no. Default: Y
* wifi(Y/N) - Y: yes, N:no. Default: N
* restaurant(Y/N) - Y: yes, N:no. Default: N
* airConditioning(Y/N) - Y: yes, N:no. Default: N
* meals(Y/N) - Y: yes, N:no. Default: N

2. Comment
* id - Primary Key.
* stars(float) - Rating from 0 to 10.
* comment - Comment by user
* hotel_id - Id of hotel on which commented.
* user_id - Id of user who commented.

3. Users
* id - Primary Key.
* userName - Name of the user
* emailId - Users emailId.

## API

#### Hotel
1. Post: api/v1/hotel

```json
{
 "name" : "Marriott International",
 "city" : "Pune",
 "date" : "2021-05-31",
 "rooms": "10",
 "availability":"Y",
 "wifi": "Y",
 "restaurant" : "Y",
 "airConditioning" : "N",
 "meals" : "Y"
}
```
2. DELETE : api/v1/hotel?id=1
   >If hotel with id 1 present the hotel gets deleted else an ERROR is thrown.

3. PUT : api/v1/hotel?id=1
   >If hotel with id 1 present the hotel gets updated else an ERROR is thrown.
```json
{
"id" : "1",
 "name" : "Marriott International",
 "city" : "Banglore",
 "date" : "2021-05-31",
 "rooms": "10",
 "availability":"Y",
 "wifi": "Y",
 "restaurant" : "Y",
 "airConditioning" : "N",
 "meals" : "Y"
}
```

4. GET - api/v1/hotel
   >Returns all hotel list with comments and users who commented if present.
```
{
"id": 1,
"name": "Marriott International",
"city": "Pune",
"date": "2021-05-31",
"rooms": 10,
"stars": 9.0,
"availability": "Y",
"wifi": "Y",
"restaurant": "Y",
"airConditioning": "N",
"meals": "Y",
"comment":[
           {
            "id": 1,
            "stars": 9.0,
            "comment": "Hotel's food was very good. And staff was also very friendly.",
            "users":{
                     "id": 1,
                      "userName": "XYZ",
                     "email": "xyz@gmail.com",
                     "createdAt": "2021-05-31T20:35:56.000+00:00"
                    }
           }
           ]
}
```
5. **To search**
   GET - http://localhost:8080/api/v1/hotel/find?search=name:M*,stars:9,airConditioning:Y

>Returns list of hotel based on the attributes passed in search
> Comparators used are :- case ':'
return EQUALITY;
case '!':
return NEGATION;
case '>':
return GREATER_THAN;
case '<':
return LESS_THAN;
case '~':
return LIKE
```
{
"id": 1,
"name": "Marriott International",
"city": "Pune",
"date": "2021-05-31",
"rooms": 10,
"stars": 9.0,
"availability": "Y",
"wifi": "Y",
"restaurant": "Y",
"airConditioning": "N",
"meals": "Y",
"comment":[
            {
             "id": 1,
             "stars": 9.0,
             "comment": "Hotel's food was very good. And staff was also very friendly.",
             "users":{
                      "id": 1,
                      "userName": "XYZ",
                      "email": "xyz@gmail.com",
                      "createdAt": "2021-05-31T20:35:56.000+00:00"
                     }
            }
           ]
}
```


#### Comment
1. Post: api/v1/comment
> If hotel with hotelId 1 and user with userId 1 is present then only comment is saved. And the hotels rating is saved.

```json
{
  "stars": "9",
  "comment": "Hotel's food was very good. And staff was also very friendly.",
  "hotelId" : "1",
  "userId":"1"
}
```
2. DELETE : api/v1/comment?id=1
   >If comment with id 1 present the comment gets deleted else an ERROR is thrown.

3. PUT : api/v1/hotel?id=1
   If comment with id 1,hotel with hotelId 1 and user with userId 1 is present then only comment is saved. And the hotels rating is saved

```json
{
  "id" : "1",
  "stars": "9",
  "comment": "Hotel's food was very good. Staff was also very friendly. Rooms were also clean.",
  "hotelId" : "1",
  "userId":"1"
}
```

4. GET - api/v1/comment
   >Returns all comment list with users who commented if present.
```
{
{
 "id": 1,
 "stars": 9.0,
 "comment": "Hotel's food was very good. Staff was also very friendly.Rooms were also clean.",
 "users":{
          "id": 1,
          "userName": "XYZ",
          "email": "xyz@gmail.com",
          "createdAt": "2021-05-31T20:35:56.000+00:00"
}
```

#### User
1. Post: api/v1/user

```json
{
  "userName": "XYZ",
  "email": "xyz@gmail.com"
}
```
2. DELETE : api/v1/user?id=1
   >If user with id 1 present the user gets deleted else an ERROR is thrown.

3. PUT : api/v1/hotel?id=1
   >If user with id 1 present the user gets updated else an ERROR is thrown.
```json
{
  "id":"1",
  "userName": "ABC",
  "email": "abc@gmail.com"
}
```

4. GET - api/v1/user
   >Returns all users list if present.
```

{
 "id": 1,
 "userName": "XYZ",
 "email": "xyz@gmail.com",
 "createdAt": "2021-05-31T17:10:01.000+00:00"
},
```

## Swagger
>For further details use Swagger UI at:http://localhost:8080/swagger-ui.html