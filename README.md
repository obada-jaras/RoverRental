# **Rover Rental**

Rover Rental is a car rental application for freelancers to rent their cars to customers, through an application, users can access the data added by the company and other users and rent any car.

This application will allow users to search for cars for rental based on specific requirements such as location, pick up and drop off dates. Users can check several information added about the cars and rent any car based on the user’s preference.

In our application, as it provides an environment for freelancers, users will be able to add cars of their own for rental, they add several information required about the car, and then it is shared on the application’s homepage for other users to view it.

## **App Activities**:
- Splash Screen
  
  ![image](https://user-images.githubusercontent.com/35347949/219206516-1a583b8b-574e-4920-ab52-acdaba9d730b.png)

- Registration Page
  
  ![image](https://user-images.githubusercontent.com/35347949/219206931-9c640916-563a-4131-bfed-437876e59a36.png)

- Log in Page
  
  ![image](https://user-images.githubusercontent.com/35347949/219206806-1e367845-3970-4162-9e75-fa495da6777a.png)

- Home Page

   ![image](https://user-images.githubusercontent.com/35347949/219208399-7e01de65-dd55-4458-a6ae-f194415967e4.png)

- Available Cars Page

   ![image](https://user-images.githubusercontent.com/35347949/219209612-c82299d7-fe33-41eb-8d21-fd8baeb8bf3f.png)

- Cars Details
  
  ![image](https://user-images.githubusercontent.com/35347949/219210987-d24f6c68-4e89-4412-bc86-0a9bebb344d7.png)

- Confirm Selection Page

   ![image](https://user-images.githubusercontent.com/35347949/219211257-594e9749-d676-4128-a2c0-3cd067569789.png)

- Payment Page

   ![image](https://user-images.githubusercontent.com/35347949/219211505-835362d6-8317-4def-bb9f-bfe270c3d1dc.png)

- Add Car Page
  
  ![image](https://user-images.githubusercontent.com/35347949/219212033-d2e79907-dccb-4bba-a3bc-8e29859dde9f.png)

- Feedback and Rating Page

   ![image](https://user-images.githubusercontent.com/35347949/219212407-46ca2515-64a2-4f4e-99ea-ab9d99020bdc.png)

- About Us Page
  
  ![image](https://user-images.githubusercontent.com/35347949/219212892-7674d4ec-fedc-4eae-ad1d-21d654f55833.png)

- Contact Us Page

   ![image](https://user-images.githubusercontent.com/35347949/219213250-3d61f504-1745-47e5-9ea9-84c1ecfe4f75.png)

- User Profile Page
  
  ![image](https://user-images.githubusercontent.com/35347949/219213832-dfc11635-473d-4dd9-9e89-2be68bd9b1f6.png)

- Update Profile Page

   ![image](https://user-images.githubusercontent.com/35347949/219213918-afb44125-60fb-4ace-a4be-a507b8cd1f98.png)

- User Activity Page
  
  ![image](https://user-images.githubusercontent.com/35347949/219215052-e16c402f-a468-4732-8f8a-b34773b0fa05.png)

<br>

## **Firebase Data Structure**
The database for this project is based on Firebase Realtime Database and Firebase Storage. The data in the Firebase Realtime Database is structured in the following format:
```
users: {
    [userID]: {
        firstName: "",
        lastName: "",
        dob: "[date of birth]",
        gender: "[Male or Female]",
        phoneNumber: "",
        email: "",
        password: "",
        activities (optional): {
            offers (optional): {
                [offerID]: ""
            },
            rentals (optional): {
                [rentalID]: ""
            },
            transactions (optional): {
                [transactionID]: ""
            },
            feedbacks (optional): {
                [feedbackID]: ""
            }
        }
    }
},

cars: {
    [carID]: {
        brand: "",
        modelYear: "",
        motorType: "",
        price: "[price per day]",
        transmission: "[Manual or Automatic]",
        seats: "[number of seats]",
        carOwner: "[userID]",
        location: "[city name]",
        lat_long: "[latitude,longitude]",
        bookings (optional): {
            [bookingID]: {
                fromDate: "",
                toDate: "",
                paymentMethod: "[onSite or Visa]",
                totalPrice: "",
                userID: "[rented user account number]"
            }
         },
         feedbacks (optional): {
            [userID]: {
               rating: "[number from 1 to 5]",
               comment: ""
            }
        }
    }
}
```

The Firebase Storage is used to save users and cars photos. User images are saved as `/images/users/[userID].png`, while car images are saved as `/images/cars/[carID].jpg`.

<br>

## **Testing the Application**
For testing purposes, you can use the following login credentials:

Email: test@gmail.com
Password: 123456ee
