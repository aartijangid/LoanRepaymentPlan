# Generate Repayment Plan

This application calculates repayment plan for an annuity loan. Therefore the amount that the borrower has to pay back every month, consist of principal and interest repayments, does not change (the last installment might be an exception).

## How to install?
1. Make sure you have java 8(or higher version) installed on your systems
2. Ensure Maven package manager wrapper(mvnw) is included in the project root directory
3. Clean and Install
	`./mvnw clean install`

## How to run tests?
1. Ensure to go through "How to install?"
2. Run test with Maven
	`./mvnw clean test` 

## How to start server?
	`java -jar target/generate-0.0.1-SNAPSHOT.jar`
	
## Testing using Postman or any other API testing tool
URL: http://localhost:8080/generate-plan
Content-Type: application/json

Request Payload:
```
{
"loanAmount": "5000", 
"nominalRate": "5.0",
"duration": 24,
"startDate": "2018-01-01T00:00:01Z"
}
```

Response Expected:
```
[
    {
        "borrowerPaymentAmount": "219.36",
        "date": "2018-01-01T00:00:01",
        "initialOutstandingPrincipal": "5000",
        "interest": "20.83",
        "principal": "198.52",
        "remainingOutstandingPrincipal": "4801.48"
    },
    {
        "borrowerPaymentAmount": "219.36",
        "date": "2018-02-01T00:00:01",
        "initialOutstandingPrincipal": "4801.48",
        "interest": "20.01",
        "principal": "199.35",
        "remainingOutstandingPrincipal": "4602.13"
    },
    ...
    {
        "borrowerPaymentAmount": "219.36",
        "date": "2019-12-01T00:00:01",
        "initialOutstandingPrincipal": "218.47",
        "interest": "0.91",
        "principal": "218.45",
        "remainingOutstandingPrincipal": "0.02"
    }
] 
```

## Approach
Test Driven Development(TDD)

## Tools and framework
1. Eclipse IDE
2. Spring Tool Suit 4 (4.3.0 version) plugin
3. Spring Boot
4. JUnit for testing
5. jackson library for object mapping (used only in test)