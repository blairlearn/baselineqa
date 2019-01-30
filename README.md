# CancerGov Digital Platform

This is the QA automation framework for the [CancerGov Digital Communications Platform](https://github.com/nciocpl/cgov-digital-platform/)


## Prerequisites:
- JDK 1.8.0_144 or later
  - [Oracle's download site](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
  - Installation Instructions
    [Windows](https://docs.oracle.com/javase/8/docs/technotes/guides/install/windows_jdk_install.html#CHDEBCCJ)
    [Mac](https://docs.oracle.com/javase/8/docs/technotes/guides/install/mac_jdk.html)
- [Apache maven](http://maven.apache.org/download.cgi) tool
  - On Mac:
    - Install [Homebrew](https://brew.sh/)
    - `brew install maven`

## Command line execution:

To run the default test suite (all tests), execute the command

    mvn test

### Specifying the environment

Hostnames are specified in the `config.properties` file, as `environment.hostname.<NAME>` (where `<NAME>` is
the human-friendly name of the environment.) Each `environment.hostname` entry is a fully qualified hostname
(e.g. `environment.hostname.prod=www.cancer.gov`).

The default runtime environment is specified as the value of the `environment.active` property.
(e.g. `environment.active=qa`)


To default environment may be overriden on the commandline by specifing `-Denvironment=<environment_name>`

    mvn test -Denvironment=prod


### Specifying a test suite.

The entire set of tests is executed by default.

To execute a specific test suite, execute the command

    mvn test -Dsurefire.suiteXmlFiles=<testfile>

Where `<testfile>` is the test suite file and path.  Multiple test suites may be specified by separating them
with commas. e.g.

    mvn test -Dsurefire.suiteXmlFiles=resources\testng-CTS.xml,resources\testng-R4R.xml

### Specifying the browser

Tests default to running in Chrome Headless. To use a different browser, include the arguement -Dbrowser=<browser>

    mvn test -Dbrowser=Chrome

Valid browser names are:
* Chrome
* ChromeHeadless
* Firefox
* GeckoHeadless
* iPhone6
* iPad

**NOTE:** Names are CaSe seNstive.  (e.g. "Chrome" is not the same as "chrome.")


