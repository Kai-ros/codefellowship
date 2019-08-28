# Codefellowship

For your remaining labs before the midterm project, you’ll create an app called CodeFellowship that allows people learning to code to connect with each other and support each other on their coding journeys.

## Challenge

Day 1: 
* Build an app that allows users to create their profile on ```CodeFellowship```.

* The site should have a splash page at the root route (```/```) that contains basic information about the site, as well as a link to the “sign up” page.
* An ```ApplicationUser``` should have a ```username```, ```password``` ( hashed using BCrypt), ```firstName```, ```lastName```, ```dateOfBirth```, ```bio```, and any other fields you think are useful.
* The site should allow users to create an ```ApplicationUser``` on the “sign up” page.
    * Your Controller should have an ```@Autowired private PasswordEncoder passwordEncoder;``` and use that to run ```passwordEncoder.encode(password)``` before saving the password into the new user.
* The site should have a page which allows viewing the data about a single ```ApplicationUser```, at a route like ```/users/{id}```.
    * This should include a default profile picture, which is the same for every user, and their basic information.
* Using the above cheat sheet, add the ability for users to log in to your app.
    * Upon logging in, users should be taken to a ```/myprofile``` route that displays their information.
* Ensure that user registration also logs users into your app automatically.
* The site should be reasonably styled. (This can be using CSS that you did not create yourself.)
* The site should contain integration testing. At a minimum, there should be tests to ensure basic functionality for the splash page and the sign up page.

Day 2: 
* The site should have a splash page at the root route (```/```) that contains basic information about the site, as well as a link to the “sign up” page.
* An ```ApplicationUser``` should have a ```username```, ```password``` (will be hashed using BCrypt), ```firstName```, ```lastName```, ```dateOfBirth```, ```bio```, and any other fields you think are useful.
* The site should allow users to create an ```ApplicationUser``` on the “sign up” page. (For now, there is no such thing as login.)
    * Your Controller should have an ```@Autowired private PasswordEncoder passwordEncoder```; and use that to run ```passwordEncoder.encode(password)``` before saving the password into the new user.
* The site should have a page which allows viewing the data about a single ```ApplicationUser```, at a route like ```/users/{id}```.
    * This should include a default profile picture, which is the same for every user, and their basic information.
* The site should be well-styled and attractive.
* The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment that is used on multiple pages.)
* The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a brief message about what went wrong.
* The site should contain integration testing. At a minimum, there should be tests to ensure basic functionality for the splash page and the sign up page.

## Approach & Efficiency
I used a recursive approach to my methods to accomplish the feature tasks.
* Time complexity: O(n)
* Space comnplexity: O(n)

## API
* ``` ./src/main/java/com.example.codefellowship```
    * ```config```
        * ``` UserDetailsServiceImplement.java```
        * ``` WebSecurityConfig.java```
    * ```controllers```
        * ``` ApplicationUserController.java```
        * ``` HomeController.java```
        * ``` PostController.java```
    * ```models```
        * ``` ApplicationUser.java```
        * ``` ApplicationUserrepository.java```
        * ``` Post.java```
        * ``` PostRepository.java```
    * ```CodefellowshipApplication.java```
* ``` ./src/main/java/resources/templates```
    * ```templates```
        * ```fragments```
            * ```banner.html```
            * ```post.html```
        * ```error.html```
        * ```individualUser.html```
        * ```login.html```
        * ```registration.html```
        * ```root.html```
## Collaborators: 
* Marisha Hoza, Kevin Couture, Padmapriya Ganapathi
