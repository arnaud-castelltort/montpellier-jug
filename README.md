# JUG

# Installation

## Database installation

Runtime: A posgresql database named "jug" must be accessible for user jug / sofree

Build time (jooq code generation): : A posgresql database named "jugbuild" must be accessible for user jug / sofree



**You need to run this app with java 8!**

```bash
> mvn install
cd montpellier-jug-wisdom
> mvn wisdom:run
```

jugbuild database is reset on each build.

```bash
> mvn clean install -Pcleandb will reset the alldatabase
```


Then open [http://localhost:9000/](http://localhost:9000/)


