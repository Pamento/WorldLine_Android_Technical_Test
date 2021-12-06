# Android technical test

The goal of this technical test is to develop a basic movie app, that follows state-of-the-art coding patterns.

### App Specification

The app is composed of two screens (see below):

* Movie list screen, which displays a list of popular movies. Each row of the list must display :
    * The title of the movie
    * A short description of the movie
    * The poster movie
    * And other informations that you might find useful

* Movie details screen, which displays more information on the movie selected:
    * All of the previous information
    * The release date
    * A background picture of the movie
    * And other informations that you might find useful

All the information can be retrieved on the following API:
[the movie db documentation](https://developers.themoviedb.org/3/movies/get-movie-details).

You can use the api key used on this call :
* [the movie list](https://api.themoviedb.org/3/movie/popular?api_key=APIKEY&language=fr=&page=1)
* [example movie detail](https://api.themoviedb.org/3/movie/580489?api_key=APIKEY&language=fr=&page=1)


### Constraints

The app must be compatible with Android phones of varying screen sizes (you can use emulators to test out different screen sizes), in portrait mode. Having it render correctly in landscape mode is an optional plus
The app must be developed with Android Studio  
The app can be developed with either Java or Kotlin - use whichever will be faster for you and with which you are confortable (Kotlin for preference, as discussed during the call)
The app must support Android 21 (minSdk) and target Android 31 (targetSdk + compileSdk)
The app must not use Jetpack Compose
You can use any resources you need   
You can and should use any libraries you want
If possible, use a SCM like Github, and try to make distinct commits, which will allow us to follow how you implemented the various functionalities.

### Recommendations

These recommendations are optional and have the purpose of guiding your choices. If you are not familiar with any of the points, don't lose too much time on them and skip them.

* Check your code using a static code quality analyzer.
  Android Lint is built in, checkstyle is useful for Java code
  (https://medium.com/fantageek/how-to-setup-android-projects-8c306aaeb519),
  and detekt for Kotlin code (https://github.com/detekt/detekt)
* Have "connected" (which call the APIs listed above) and "offline"
  (which use offline, local json files) build variants
* Use a dependency injection framework - Dagger Hilt (https://dagger.dev/hilt/quick-start) or Koin (https://insert-koin.io/) are relatively easy to implement
* Use design patterns like MVP/MVVM/MVI/... and "Clean Architecture" / "SOLID" in the application, as you have the need for them


### Test goals

* Code and project organisation
* Library usage
* The project should be in a reviewable state, like a merge request, with distinct commits

If you have any question, feel free to contact Mihai.  
