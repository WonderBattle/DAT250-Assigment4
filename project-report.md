# DAT250: Software Technology Experiment Assignment 3 - Hand-in Report

## Project Overview
Successfully implemented a Single Page Application (SPA) frontend using Svelte that connects to the existing Spring Boot REST API from Assignment 2. The application provides a complete voting system with poll creation and voting functionality, featuring a modern user interface with proper error handling and responsive design.

## Technical Problems Encountered and Solutions


### 1. **Svelte Component State Management**
**Problem**: Initially attempted to use Svelte stores for state management, but realized they were unnecessary for this simple application and created confusion.

**Solution**: Simplified to direct component state management using regular variables and props:
```javascript
let polls = [];
let loading = false;
let error = null;
```

### 2. **API Response Handling**
**Problem**: Inadequate error handling in fetch requests leading to unclear error messages when backend was unavailable.

**Solution**: Enhanced error handling with proper status checking and error messages:
```javascript
const response = await fetch('http://localhost:8080/polls');
if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Server error: ${response.status} - ${errorText}`);
}
```

### 3. **Form Validation Challenges**
**Problem**: Creating dynamic form fields for poll options with add/remove functionality was initially complex.

**Solution**: Implemented array-based option management with index tracking:
```javascript
let options = ['', '']; // Start with two empty options

function addOption() {
    options = [...options, ''];
}

function removeOption(index) {
    if (options.length > 2) {
        options = options.filter((_, i) => i !== index);
    }
}
```


### 4. **Real-time Data Updates**
**Problem**: Vote counts didn't update automatically after voting without manual page refresh.

**Solution**: Implemented automatic data reloading after successful operations:
```javascript
async function handleVote() {
    await voteApi.castVote(...);
    await loadPolls(); // Reload data to get updated vote counts
}
```

### 5. **Frontend Delete Button Shown for Wrong User**
**Problem**: When logged in as *Alice*, the frontend displayed the delete button for polls created by *Anthony*, and vice versa. This allowed the wrong user to delete polls.

**Solution**: Fixed the conditional rendering in the Svelte components so that the delete button is only shown if `poll.creator === currentUser`. This ensured users can only delete their own polls.

### 6. **Integrating Frontend Build with Spring Boot**

**Problem**: Initially, the frontend and backend had to be run separately (`npm run dev` for frontend, `./gradlew bootRun` for backend). Serving everything together from Spring Boot did not work.

**Solution**: Configured Gradle to:

* Run `npm run build` inside the `frontend/` folder
* Copy the generated `dist/` files into `backend/src/main/resources/static/`
* Hook this process into `processResources`, so the frontend always rebuilds before starting Spring Boot.

Now, a single `./gradlew bootRun` launches both backend and frontend seamlessly.

### 7. **CI/CD Build Issue with Frontend Integration**

**Problem**: GitHub Actions failed to build the frontend with the error:

```
You are using Node.js 22.0.0. Vite requires Node.js version 20.19+ or 22.12+.
Error [ERR_MODULE_NOT_FOUND]: Cannot find package 'vite'
```

**Solution**:

* Updated Node.js version in Gradle config to `22.12.0`:

```kotlin
node {
    version.set("22.12.0")
}
```

* Ensured `npm install` runs before `npm run build` by adding:

```kotlin
tasks.register<NpmTask>("runBuild") {
    dependsOn("npmInstall")
    args = listOf("run", "build")
    dependsOn("npmInstall") // install before build
}
```

After these changes, CI successfully builds and runs all tests.

---


## Test Scenario
The application supports the following test scenario:

1. **Application Loading**: Open http://localhost:8080 and verify the voting app loads without errors
2. **User Creation**: Click "Create User" button and create a user with username and email
3. **Poll Creation**: Click "Create New Poll" button and create a poll with multiple options
4. **Poll Display**: Return to main view and verify the created poll appears in the list
5. **Voting Functionality**: Select a vote option and cast a vote successfully
6. **Error Handling**: Test error scenarios (empty forms, backend unavailable)
7. **Responsive Design**: Verify the interface works on different screen sizes

## Link to Code

- Code from Assigment 1: https://github.com/WonderBattle/DAT250-Assigment1
- Code from Assigment 2: https://github.com/WonderBattle/DAT250-Assigment2



## Key Features Implemented

### ✅ Frontend Components
- **CreatePollComponent.svelte**: Complete poll creation form with dynamic option management
- **CreateUserComponent.svelte**: Create an user to create polls and vote in the polls
- **VoteComponent.svelte**: Voting interface with option selection and vote submission
- Responsive design with modern CSS styling

### ✅ API Integration
- RESTful API communication with Spring Boot backend
- Proper error handling and loading states
- Real-time data updates after mutations

### ✅ User Experience
- Intuitive poll creation with add/remove option functionality
- Clean voting interface with visual feedback
- Comprehensive error messages and validation

### ✅ Technical Implementation
- Pure Svelte components without external dependencies
- Modern CSS with flexbox/grid layouts
- Proper component separation and reusability
- Efficient state management

## Pending Issues

### 1. **User Authentication**
The application currently uses hardcoded user IDs for demonstration. A proper user authentication system with login/logout functionality needs to be implemented.

### 2. **Real-time Updates with WebSocket**
Lack of WebSocket implementation for fully bidirectional communication and live vote updates without page refresh.

### 3. **Caddy Reverse Proxy Setup**
Missing unified deployment with Caddy server to serve both frontend and backend through a single endpoint.

### 4. **GraphQL API Migration**
REST API has not been replaced with GraphQL for more flexible data fetching and reduced over-fetching.

### 5. **SvelteKit Migration**
SPA remains as static assets instead of using SvelteKit for server-side rendering and improved SEO.

### 6. **Advanced Validation**
More comprehensive form validation including duplicate option detection and poll expiration date validation.

### 7. **Persistent User Sessions**
Implementation of proper session management to maintain user state across browser sessions.

### 8. **Accessibility Features**
Additional ARIA labels and keyboard navigation support for better accessibility compliance.



## Conclusion
The assignment was successfully completed with a functional SPA frontend that integrates seamlessly with the existing Spring Boot REST API. The main challenges involved CORS configuration and proper state management in Svelte components, but all critical issues were resolved. The application provides a modern, responsive voting interface with complete CRUD functionality. The experience provided valuable insights into SPA development with Svelte and frontend-backend integration patterns.

I have learned a lot about modern web development practices, component-based architecture, and REST API consumption. The clean separation of concerns and responsive design make the application both maintainable and user-friendly.