# [TSEC: A type-safe, functional, general purpose security and cryptography library.](https://jmcardon.github.io/tsec/)

This fork just holds some changes that I needed for my deployments for now, you should go to the root project.

[![Join the chat at https://gitter.im/tsecc/Lobby](https://badges.gitter.im/tsecc/Lobby.svg)](https://gitter.im/tsecc/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/jmcardon/tsec.svg?branch=master)](https://travis-ci.org/jmcardon/tsec)
[ ![Latest Version](https://maven-badges.herokuapp.com/maven-central/io.github.jmcardon/tsec-common_2.12/badge.svg)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A"io.github.jmcardon"%20tsec)


### Latest Release: 0.2.0.1

For the current progress, please refer to the [RoadMap](https://github.com/jmcardon/tsec/wiki)

For version changes and additions, including breaking changes, see either [release notes](https://github.com/jmcardon/tsec/releases)
or the [Version Changes](https://github.com/jmcardon/tsec/wiki/Version-Changes) page, or the [CHANGELOG](https://github.com/clovellytech/tsec/) for this fork

## Note about using Windows￼™® with tsec

Windows™® is not supported.

Feel free to fork the project and add your own windows support.


### Note on milestones:
Our Notation for versions is:
```
X.X.X.X
      ^__Fork appendage (includes all features of major/minor from upstream)
^ ^ ^____Minor
| |______Major
|________Complete redesign   
```

All `x.x.x-Mx` releases are milestone releases. Thus, we do not guarantee binary compatibility or no api-breakage until
a concrete version(i.e `0.0.1`). We aim to keep userland-apis relatively stable, but 
internals shift as we find better/more performant abstractions.

To get started, if you are on sbt 0.13.16+, add


| Name                  | Description                                              | Examples |
| -----                 | ----------                                               | -------- |
| tsec-common           | Common crypto utilities                                  |          |
| tsec-password         | Password hashers: BCrypt and Scrypt                      | [here](https://github.com/jmcardon/tsec/blob/master/examples/src/main/scala/PasswordHashingExamples.scala)|
| tsec-cipher-jca       | Symmetric encryption utilities                           | [here](https://github.com/jmcardon/tsec/blob/master/examples/src/main/scala/SymmetricCipherExamples.scala)|
| tsec-cipher-bouncy    | Symmetric encryption utilities                           | [here](https://github.com/jmcardon/tsec/blob/master/examples/src/main/scala/SymmetricCipherExamples.scala)|
| tsec-mac              | Message Authentication                                   | [here](https://github.com/jmcardon/tsec/blob/master/examples/src/main/scala/MacExamples.scala)|
| tsec-signatures       | Digital signatures                                       | [here](https://github.com/jmcardon/tsec/blob/master/examples/src/main/scala/SignatureExamples.scala)|
| tsec-hash-jca         | Message Digests (Hashing)                                | [here](https://github.com/jmcardon/tsec/blob/master/examples/src/main/scala/MessageDigestExamples.scala)|
| tsec-hash-bouncy      | Message Digests (Hashing)                                | [here](https://github.com/jmcardon/tsec/blob/master/examples/src/main/scala/MessageDigestExamples.scala)|
| tsec-libsodium        | Nicely-typed Libsodium JNI bridge                        | [here](https://github.com/jmcardon/tsec/blob/master/examples/src/main/scala/MessageDigestExamples.scala)|
| tsec-jwt-mac          | JWT implementation for Message Authentication signatures | [here](https://github.com/jmcardon/tsec/blob/master/examples/src/main/scala/JWTMacExamples.scala)|
| tsec-jwt-sig          | JWT implementation for Digital signatures                | [here](https://github.com/jmcardon/tsec/blob/master/examples/src/main/scala/JWTSignatureExamples.scala)|
| tsec-http4s           | Http4s Request Authentication and Authorization          | [here](https://github.com/jmcardon/tsec/tree/master/examples/src/main/scala/http4sExamples)|

To include any of these packages in your project use:

```scala
val tsecV = "0.2.0.1"
libraryDependencies ++= Seq(
    "tsec-common",
    "tsec-password",
    "tsec-cipher-jca",
    "tsec-cipher-bouncy",
    "tsec-mac",
    "tsec-signatures",
    "tsec-hash-jca",
    "tsec-hash-bouncy",
    "tsec-libsodium",
    "tsec-jwt-mac",
    "tsec-jwt-sig",
    "tsec-http4,
).map("com.clovellytech % _ % tsecV)
```

## Note on contributing

See CONTRIBUTING.md
 
## A note on logging
We use `log4s` which is a logging facade over [SLF4J](https://www.slf4j.org/). This implies you need to add a
_binding_ to your classpath. Check https://www.slf4j.org/codes.html#StaticLoggerBinder
 
## Big Thank you to our contributors (direct or indirect):
[Robert Soeldner](https://github.com/rsoeldner) (Contributor/Maintainer)

[Christopher Davenport](https://github.com/ChristopherDavenport)(Contributor/Maintainer)

[Harrison Houghton](https://github.com/hrhino)(Contributor/Maintainer)

[Bjørn Madsen](https://github.com/aeons) (Contributor)

[André Rouél](https://github.com/before)(Contributor)

[Edmund Noble](https://github.com/edmundnoble) (For the dank tagless)

[Fabio Labella](https://github.com/systemfw) (For the great FP help)

[Will Sargent](https://github.com/wsargent) (Security Discussions)

