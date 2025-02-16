Void Return Type
---------
You cannot return nothing.

You can define a return type which is nullable e.g.

type Mutation {
  addElement(element: ElementData): ID
  removeElement(id: ID): Boolean
}

Java Util Map in GraphQL
-------------------
There is no map type in GraphQL. Because maps are basically containing dynamic keys and, they do not play well into the static types that GraphQL is expecting.

If you are really unsure of what type the data is going to be in that map, you can always store that dynamic information as a String. This is something I personally would do.


How to send list of String in mutation query
-----------------
mutation CreateAppUser {
    createAppUser(
        inUser: {
            firstName: "Vidya"
            lastName: "Balan"
            mStatus: MARRIED
            phoneNos: ["111","222"]
            bankName: "HSBC Bank"
            accountNo: "ACTNO-456"
            
        }
    ) {
        mStatus
    }
}


How to access graphiql
-----------
provide the following property in application.properties

spring.graphql.graphiql.enabled=true

http://localhost:8081/graphiql?path=/graphql


How to use Interface in GraphQL
-----------
In schema.graphqls, define the below

type Query {
	getAllTypesOfUser: [User]
}

interface User {
    id : ID!
    fullName : String!
    cityName: String
    phoneNos: [String]
}

type ContractUser implements User  {
    id : ID!
    fullName : String!
    cityName: String
    phoneNos: [String]
}

type FulltimeUser implements User  {
    id : ID!
    fullName : String!
    cityName: String
    phoneNos: [String]
}

Java Controller implementation

Create interface like this

public interface User {

}


@Data @NoArgsConstructor @AllArgsConstructor
public class FulltimeUser implements User {

	private long id;
    private String fullName;
    private String cityName;
    private List<String> phoneNos;
}


@QueryMapping
	public Collection<User> getAllTypesOfUser() {
		return List.of(
				new ContractUser(123L,"John Abraham","Bangalore", List.of("111","222")),
				new FulltimeUser(456L,"Vidya Balan","Chennai", List.of("6666","999"))
				);
	}
	

Graphql query is given below.

{
  getAllTypesOfUser {
    id
    fullName
    cityName
    phoneNos
  }
}


Response is given below.

{
  "data": {
    "getAllTypesOfUser": [
      {
        "id": "123",
        "fullName": "John Abraham",
        "cityName": "Bangalore",
        "phoneNos": [
          "111",
          "222"
        ]
      },
      {
        "id": "456",
        "fullName": "Vidya Balan",
        "cityName": "Chennai",
        "phoneNos": [
          "6666",
          "999"
        ]
      }
    ]
  }
}


Another type of Query
-----------
type Query {
	getBookByName(name: String): Book
}

Actual graphql query is given below.

query GetBookByName {
    getBookByName(name: "bookName") {
        isbnNo
        name
        authors {
            name
            books {
                name
                isbnNo
            }
        }
    }
}

Response from the server.
{
    "data": {
        "getBookByName": {
            "isbnNo": "Some ISBN No",
            "name": "A Good Book",
            "authors": [
                {
                    "name": "James Bond",
                    "books": [
                        {
                            "name": "A Good Book",
                            "isbnNo": "Some ISBN No"
                        }
                    ]
                },
                {
                    "name": "Charles Dickens",
                    "books": [
                        {
                            "name": "A Good Book",
                            "isbnNo": "Some ISBN No"
                        }
                    ]
                }
            ]
        }
    }
}

Update operation
---------
type Mutation {
	updateAppUserByAddress(inUser: AppUserInput): String
}

Corresponding Controller Method

@MutationMapping
	public String updateAppUserByAddress(@Argument AppUserInput inUser) {
		System.out.println("User Details for address update: "+inUser);
		return "AppUser address updated successfully ...";
	}
	
Actual GraphQL Query
mutation UpdateAppUserByAddress {
    updateAppUserByAddress(inUser: { cityName: "Hyderabad" })
}

Response from Server
{
    "data": {
        "updateAppUserByAddress": "AppUser address updated successfully ..."
    }
}

Delete Operation
------
type Mutation {
	deleteAppUser(userId: String): AppResult
}

Coresponsing Controller method

@MutationMapping
	public AppResult deleteAppUser(@Argument String userId) {
		System.out.println("What is the userId to be deleted: "+userId);
		return new AppResult("AppUser information deleted successfully ...");
	}

Actual GraphQL query

mutation DeleteAppUser {
    deleteAppUser(userId: "1234") {
        result
    }
}

Response from Server

{
    "data": {
        "deleteAppUser": {
            "result": "AppUser information deleted successfully ..."
        }
    }
}	