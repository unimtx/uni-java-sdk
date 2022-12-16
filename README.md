# Unimatrix Java SDK

[![Maven Central](https://img.shields.io/maven-central/v/com.unimtx/uni-sdk.svg)](https://mvnrepository.com/artifact/com.unimtx/uni-sdk) [![Release](https://img.shields.io/github/release/unimtx/uni-java-sdk.svg)](https://github.com/unimtx/uni-java-sdk/releases/latest) [![GitHub license](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/unimtx/uni-java-sdk/blob/main/LICENSE)

The Unimatrix Java SDK provides convenient access to integrate communication capabilities into your Java applications using the Unimatrix HTTP API. The SDK provides support for sending SMS, 2FA verification, and phone number lookup.

## Getting started

Before you begin, you need an [Unimatrix](https://www.unimtx.com/) account. If you don't have one yet, you can [sign up](https://www.unimtx.com/signup?s=java.sdk.gh) for an Unimatrix account and get free credits to get you started.

## Documentation

Check out the documentation at [unimtx.com/docs](https://www.unimtx.com/docs) for a quick overview.

## Installation

The recommended way to install the Unimatrix SDK for Java is with a dependency management tool such as Maven or Gradle, which is available on [Maven Central](https://mvnrepository.com/artifact/com.unimtx/uni-sdk).

### Maven users

Add this dependency to your project's `pom.xml` file:

```xml
<dependency>
    <groupId>com.unimtx</groupId>
    <artifactId>uni-sdk</artifactId>
    <version>0.2.0</version>
</dependency>
```

### Gradle users

Add this dependency to your project's `build.gradle` file:

```bash
implementation "com.unimtx:uni-sdk:0.2.0"
```

## Usage

The following example shows how to use the Unimatrix Java SDK to interact with Unimatrix services.

### Send SMS

Send a text message to a single recipient.

```java

import com.unimtx.Uni;
import com.unimtx.UniException;
import com.unimtx.UniResponse;
import com.unimtx.model.UniMessage;

public class Example {
    public static String ACCESS_KEY_ID = "your access key id";
    private static String ACCESS_KEY_SECRET = "your access key secret";

    public static void main(String[] args) {
        Uni.init(ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        UniMessage message = UniMessage.buildMessage()
            .setTo("your phone number") // in E.164 format
            .setSignature("your sender name")
            .setContent("Your verification code is 2048.");

        try {
            UniResponse res = message.send();
            System.out.println(res.data);
        } catch (UniException e) {
            System.out.println("Error: " + e);
            System.out.println("RequestId: " + e.requestId);
        }
    }
}

```

## Reference

### Other Unimatrix SDKs

To find Unimatrix SDKs in other programming languages, check out the list below:

- [Go](https://github.com/unimtx/uni-go-sdk)
- [Node.js](https://github.com/unimtx/uni-node-sdk)
- [Python](https://github.com/unimtx/uni-python-sdk)
- [PHP](https://github.com/unimtx/uni-php-sdk/)

## License

This library is released under the [MIT License](https://github.com/unimtx/uni-java-sdk/blob/main/LICENSE).
