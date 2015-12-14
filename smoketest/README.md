## Restful web service functional tests
This project is a [Cucumber](http://cukes.info) suite that consists of a non-exhaustive set of smoke tests for the
restful web service. It is not intended as a full acceptance suite.


## Usage
To run the suite, Git Bash window to /c/aaaProjects/perso/springTutorials/restfulWebService/smoketest
    `gradle clean test --info -Pprofile=local`
    `gradle clean test --info -Pprofile=ci`
Note that it is really important to add the --info, as this is the logging level at which a lot of useful Cucumber output
is spewed out at. Generated steps, for instance.


## World
Currently, we have a BaseSteps class which statically loads some properties at startup. The idiomatic Cucumber
standard is to have an object called a 'World' which maintains such state. Such a class has been created for this
purpose, called, naturally, World. Further, we are leveraging cucumber-picocontainer to inject instances of this
World class into steps. Why Picocontainer? Cucumber-JVM supports a number of dependency injection containers (Spring,
Guice, Picocontainer, something called 'Weld') but Picocontainer support is the most mature, and the lightest touch
in terms of transitive dependencies.

Ultimately, the intent is that anything that is not a Cucumber step definition will not live in any steps classes.
This may seem a little unusual to a Java developer but a quirk of Cucumber-JVM is that you cannot *extend* any class
which has @Before or @After hooks, so should we wish to provide such hooks, a BaseSteps class is not the way to achieve
that. (Note that those annotations are from the package *cucumber.api.java* **not** *org.junit*).
