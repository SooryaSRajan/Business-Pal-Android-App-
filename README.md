# Android-Study-Jams

Business Vendor App

<b> Problem Statement: </b>

Small scale business vendors always struggle to get their business up as they are dominated by bigger and wholesale businesses and people are usually unaware of existence of such small scale business vendors. Mobile applications can be used to advertise and publish information about business vendors or new business owners as mobile apps are widely used and is best suited.

<b> Proposed Solution : </b>

This project proposes a simple full stack app "Business Pal" to help small business vendors advertise and promote their business. The app allows any user to add information about their business, an email to let anyone contact them and the  app also allows them to attach their business location through GPS to people can go visit the province. The app also allows users to open the location in an inbuilt Map to quickly view the location, and also allows them to open it in google maps for extended features. The project's scope is to add more security, allow modifications in the future, add filter for business types and add option to view local business.
  
<b> Functionality & Concepts used : </b>

- The app uses a simple yet intuitive easy to use interface, using Android and Material components for the User Interface. The components used in the app is attached below: 
- Constraint Layout : All of the available layouts are built fully responsive using constraint layout. 
-  Scroll View: Some of the layouts are also enclosed using ScrollView to allow pages which are in the risk of overflowing in smaller displays or landscape mode scroll.
- RecyclerView : To list all the current available business vendors in a list efficiently, recycler view is used.
- Google Maps API : Google Maps API is used to display the location of the business vendors quickly within the app.
- LiveData & Room Database : Live Data and View model is used to observe and update the recycler view whenever a network change is detected or the user clicks on the reload button. Room Database is used in the View Model to store data whenever requested and retrieve the data to display in the recycler view whenever network is not available to allow the user to have a seemless experience regardless.
- Retrofit : Retrofit is used to make GET and POST requests to the online server which manages all the Business vendors data to get data to display in the app.
- Firebase Cloud Messaging : Firebase Cloud Messaging (FCM) is used to deliver notifications realtime to all available users and notify them about newly added Business in the app.

<b> Application Link & Future Scope : </b>

Backend Database: https://github.com/SooryaSRajan/Business-Pal-NodeJS-Backend (Built using Node JS, Express and MongoDB)

Screenshots: https://drive.google.com/drive/folders/1DyAoe0IbYWcoLKn9SGQLZhxf1wOeMZuo?usp=sharing


