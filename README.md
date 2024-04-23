Documentation contains class diagram：
![image](https://github.com/SJHWR/JAVA_final/assets/161080112/b819d957-c47f-489d-ba7e-fd27a56b3f90)




Division of labor among project members：

Jingwen Liu: 
UI design, overall program construction, and all implementations except Basic functions.

Gaoshuo Cui:
Writing implementation plans, drawing UML diagrams, implementing Basic functions, writing final reports and recording demonstration videos.




Documentation contains list of features and their scores：

Score for all features: 36 points






Basic function (10 points):

1) Get the data through Rest API and display the data in the UI of the Android application
2) Users can search for various information using the name of the city
   ![image](https://github.com/SJHWR/JAVA_final/assets/161080112/c43b1600-01dd-443c-b84f-d2f010ecffc7)

Users can use this application to check current weather conditions, temperature, general city information, and city coordinates.As the picture shows.

3) The app can display population and demographic changes, workplace self-sufficiency, and employment rates, etc.
![image](https://github.com/SJHWR/JAVA_final/assets/161080112/3feafea1-3451-4f64-8047-5f76150678a4)



Other functions:

1) Display images (2 points)

2) Use 3 APIs (total of 6 points):
   
⚫ QWeather GeoAPI （Fuzzy query, query city latitude and longitude）

https://dev.qweather.com/en/docs/api/geoapi/

⚫ QWeather Weather API (Query real-time temperature and weather information of the city)

https://dev.qweather.com/en/docs/api/weather/

⚫ Google Map API （Used to display geographic location）

https://developers.google.com/maps

3) Data Visualization (5 points)

4) Using Fragments (use on map page, 4 points)




Customized Fuctions（Feature X）：

We would like to request the following four additional features.

1) Fuzzy query function (2 points)
   
The fuzzy query function is a feature that allows the user to search for desired results with incomplete or slightly incorrect input. For example, if someone wants to know about the city "Lahti", but due to a typing error he writes "Laht", the fuzzy query will know that you are looking for the city "Lahti" and provide you with the correct search results. And provide you with the correct city information.As shown in the image below.
![image](https://github.com/SJHWR/JAVA_final/assets/161080112/46a800bf-9ed3-4247-aa60-20383e299fd9)
![image](https://github.com/SJHWR/JAVA_final/assets/161080112/9c8b2cd1-daef-44d5-91ad-37c1f401fb7e)


2) Real-time weather function (2 points)
   
Real-time weather feature means that the application can display real-time weather conditions, temperature, etc. for the relevant city. For example, the real-time weather function allows the user to find out the weather conditions and temperature of Lahti, where he/she wants to go, so that he/she can prepare the right clothes for his/her trip.As shown in the image below.
![image](https://github.com/SJHWR/JAVA_final/assets/161080112/941f25a8-efa8-47d9-9f0b-406325af0d3f)


3 ）Function to find latitude and longitude (2 points)

The function to find latitude and longitude is to display the longitude and latitude of the searched city in the application to know the geographic location of the city.As shown in the image below.
![image](https://github.com/SJHWR/JAVA_final/assets/161080112/34c7466f-7088-481a-b439-1b42e571af5f)


4) Visualization map function (3 points)
   
The visual map function refers to displaying geographical information on the map in the form of graphics through code. Through the visual map function, users can intuitively view the distribution of geographical information on the map.As shown in the image below.
![image](https://github.com/SJHWR/JAVA_final/assets/161080112/5861c901-acf3-4105-88c3-65303273daa0)
![image](https://github.com/SJHWR/JAVA_final/assets/161080112/8f59d436-21cb-4fd5-a8a4-0786773058ac)




General description about the project：

Our project "Understanding Finnish Cities" is an Android application that can provide users with some information about Finnish cities, including real-time weather and temperature, geographical coordinates, and basic city information. This application uses multiple APIs and can display the specific conditions of the city through a map.




Documentation contains working installation/running instructions (application starts correctly with instructions)

The program is installed via apk package.

After starting the program, you will enter the main interface (search interface), and search by entering the city name in the input box of this interface and confirming.

After confirming the search, you will enter the information page, which displays various information about the searched city (current weather, statistical information).

There is also a "Show Map" button on the information page. After clicking, the map information of the searched city will be displayed. The map defaults to showing all of Finland, with pushpins marking the locations of the cities being searched for. The map can be zoomed in to see map details of the city.
