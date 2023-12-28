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
    <version>0.3.0</version>
</dependency>
```

### Gradle users

Add this dependency to your project's `build.gradle` file:

```bash
implementation "com.unimtx:uni-sdk:0.3.0"
```

## Usage

The following example shows how to use the Unimatrix Java SDK to interact with Unimatrix services.

### Initialization

```java
import com.unimtx.Uni;

public class Example {
    private static String ACCESS_KEY_ID = "your access key id";
    private static String ACCESS_KEY_SECRET = "your access key secret";

    public static void main(String[] args) {
        Uni.init(ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }
}
```

or you can configure your credentials by environment variables:

```sh
export UNIMTX_ACCESS_KEY_ID=your_access_key_id
export UNIMTX_ACCESS_KEY_SECRET=your_access_key_secret
```

### Send SMS

Send a text message to a single recipient.

```java
import com.unimtx.Uni;
import com.unimtx.UniException;
import com.unimtx.UniResponse;
import com.unimtx.model.UniMessage;

class Example {
    public static void main(String[] args) {
        Uni.init();

        UniMessage message = UniMessage.build()
            .setTo("+1206880xxxx") // in E.164 format
            .setText("Your verification code is 2048.");

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

### Send verification code

Send a one-time passcode (OTP) to a recipient. The following example will send a automatically generated verification code to the user.

```java
import com.unimtx.Uni;
import com.unimtx.UniResponse;
import com.unimtx.model.UniOtp;

class Example {
    public static void main(String[] args) {
        Uni.init();

        UniResponse res = UniOtp.build()
            .setTo("+1206880xxxx")
            .send();

        System.out.println(res.data);
    }
}
```

### Check verification code

Verify the one-time passcode (OTP) that a user provided. The following example will check whether the user-provided verification code is correct.

```java
import com.unimtx.Uni;
import com.unimtx.UniResponse;
import com.unimtx.model.UniOtp;

class Example {
    public static void main(String[] args) {
        Uni.init();

        UniResponse res = UniOtp.build()
            .setTo("+1206880xxxx")
            .setCode("123456") // the code user provided
            .verify();

        System.out.println(res.valid);
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
- [Ruby](https://github.com/unimtx/uni-ruby-sdk)
- [.NET](https://github.com/unimtx/uni-dotnet-sdk)

## License

This library is released under the [MIT License](https://github.com/unimtx/uni-java-sdk/blob/main/LICENSE).
