# TO DO List
## Track of tasks to be done

### ⬆️ [Continuation] Communicate with another server

- Try to create another client with WebClient instead of RestTemplate
- Review resilience pattern for calls (Circuit breaker? Others?)
- Review Auth in general

### 📋 Sanitize data on input
- Can be done via a custom annotiation

### 📋 Async flow (Job) to validate suggestions
- IDEA: Maybe compare authors to a valid authors dictionary for example

### 📋 Make books clickable in the front end and display the first page of content
- IDEA: Handle the pages size given a font size in the frontend

## Track of tasks already done

### ✅ Create Controller for suggestions

- Create controller for suggestions
- Create Service to handle logic for that controller
- Decouple suggestions being in the same domain that books
- Add an integration test

### ✅ Communicate with another server
- Think of a valid scenario for my app
- Use JSONPlaceholder
- Finish the CommentClient flow and tests
