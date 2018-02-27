# Bots with Spring Boot & Java

This is a sample Spring Boot app which is created to test AI based vision and speech cloud based services. 

## Amazon Polly

Class CustomPolly represents usage of Amazon Polly for creating/synthesising the audio from text. An audio player, AdvancedPlayer is used to play the audio generated using Amazon Polly service.

## Amazon S3 

Class AWSCloudStorage provides sample implementation for instantiating Amazon S3 service. 

## AWS STS (Security Token Service)

AppConfig creates a SessionCredential bean which is created using AWS access key id, access key secret and session token. The same is used in AWSCLoudStorage implementation to instantiate Amazon S3.

## Twilio API

The project demonstrates how to use Twilio phone service to play audio file stored in AWS S3. The audio file is created using Amazon Polly service.



# Related Posts

The following are some of the posts related to source code in this project:
* [Twilio & AWS S3 using Java & Spring Boot – Code Example](https://vitalflux.com/twilio-aws-s3-using-java-spring-boot-code-example/)
* [AWS Temporary Credentials with Java & Spring Boot](https://vitalflux.com/aws-temporary-credentials-java-spring-boot/)
* [Amazon S3 with Spring Boot & Java – Sample Code](https://vitalflux.com/amazon-s3-spring-boot-java-sample-code/)

* []
