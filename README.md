# RoverRental


 Rover Rental Company (RRC) is a company that provides rental system for freelancers to rent their cars to customers, through an application, users can access the data added by the company and other users and rent any car with direct connection with the renters. 

   This application will allow users to search for cars for rental based on specific requirements such as location, pick up and drop off dates. Users can check several information added about the cars and rent any car based on the user’s preference. 

   In our company, as it provides an environment for freelancers, users will be able to add cars of their own for rental, they add several information required about the car, and then it is shared on the application’s homepage for other users to view it.

   The work on the application was divided between the project’s members as the following:

## ** Marwa’s Part: **

### Splash Screen:
   This view is the opening of our application, it contains the logo of our company and our motto text, an animation is added to both of them, the logo has a top animation applied to it where it slides from the top of the screen to the middle, while the motto text has an immediate typing effect added to it. 

### Log in Page:
   In this view, the user can log in using his account to experience the different features of our application, it contains two fields to fill to be able to log in to his account, the username is an email with validation of email format, and a password that should be at least 6 characters long, the log in button takes the user into the home page of the application. 
   This view also contains the registration button that takes the user to the registration view to create an account for the application. 
   Currently there is not any data of accounts connected to the application, this will be created and worked on in the future. 

### Available Cars Page: 
   After the user searches for cars based on his preference in the homepage, this view will display the cars available based on the provided data, each car will have its own card displaying its information and a picture about the car. As the user presses the car’s card, it will move him to the car’s information view. 
   To present available cars based on the search, it will be worked on for future work when doing the backend programming. 

### Feedback and Rating Page:
   In this view, the user can add any thoughts and feedback they have regarding the application, also, they can rate their experience using the application. This view contains a rating bar of 5 stars for the user to rate the application, and each star contains a text describing the rating that is hidden and pops up when the rating is given, also, it contains a multi-line text box for the users to write any feedback they would like to give, the send feedback button is supposed to add the feedback given to the user’s activity which will be added in future work, also, this view will be added to every car view for the users to be able to add feedback regarding specific cars and renters. 

## ** Obada’s Work: **

### Registration Page:
   In this view, the user can create a new account to be able to use the application, the registration form contains 6 different fields to fill, first and last name, gender, email, phone number and password, the registration button creates the account and moves the user into the home page, in case the user misses and already has an account, the sign in button takes the user back to sign in using his existent account. 
   Currently there is not any data that the registration adds to, this will be created and worked on in the future

### Home Page:
   This view is the main page of the application, it contains a search layout where the user can add the pick up location, add pick up and drop off dates with validation that allows the user to search for available cars based on the requirements added, then moves the user to a view that contains available cars based on the data provided. The home page view also shows cars available in general and a bar at the bottom of the screen for the user to move between different views in the application. 
   A database containing more cars will be created and worked on in the future.



### Cars Information:
   This view shows information about any car that the user presses, it contains an image of the car, a layout showing different information about the car, reviews and ratings available that are added by other users and a rent this car button that moves the user into the renting a car view.
   A specific feedback and rating view will be added into every car in the future so that users can add specific feedbacks.

## ** Lian’s Work: **

### Add Car Page:
  This view allows users to offer their cars for rental, different fields containing the car’s information such as the car brand, image upload,  model , fuel, seats and more data is to be filled for other users to check when wanting to rent the car. 
   For future work, after adding the car’s data, then pressing on the add car button, it will add the car into the cars’ available view.

### Confirm Selection Page:
   This view is for users to confirm their selection after choosing the car they want to rent, it contains the car’s picture, the booking details such as location, pick up and drop off dates, price break down such as information about the payment, fees included and the total price, lastly, it allows the user to select the payment method, the users can pay by visa which takes the user into the payment by visa view, or the user can pick to pay upon pick up.

### Payment Page:
   This view contains the payment by visa view, it asks for the information needed (credit card information) for the user to pay online, it asks for the visa card number and other data required on the visa such as the expiration date, cvv number and mobile number. 
   This view will validate that all the needed information is correct and entered by handling the possible errors that might occur as the user enters the data.

## ** Dalia’s Work: **

### About Us Page:
   This view gives the users an overview of our company, an explanation of who we are, our motto and what we do, this view also allows the users to access the contact us view.

### Contact Us Page:
   This view provides the user with contact information of the company such as the phone number and email.

### User Profile Page: 
   This view will contain information of the user logged in to the application, the view shows the name, email, phone number and more data about the user.
   In order to work on those views later with real data, virtual information has been worked on in that view, and views related to it have been handled using shared preference technology. 
   From this view, users can access two other views: one for editing user data “Update Profile view” and the other for viewing user activity within the application “User Activity view”.

### Update Profile Page:
   This view allows users to make changes to their profile data which is then automatically updated in the user profile view using shared preference technology, from this view, user can go back to the user profile view from the back to profile button.

### User Activity Page:
   This view shows the user’s activity within the application such as any feedback given, payment transactions made and cars rental activity. 
Additionally, in this section, fictional data has been presented before dealing with actual data that will be related to any activity the user does in the application. Also, the user can return to the "user profile" view using the back button.


