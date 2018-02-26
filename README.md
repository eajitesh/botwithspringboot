# Bots with Spring Boot & Java

This is a sample Spring Boot app which is created to test AI based vision and speech cloud based services. 

## Amazon Polly

Class CustomPolly represents usage of Amazon Polly for creating/synthesising the audio from text. An audio player, AdvancedPlayer is used to play the audio generated using Amazon Polly service.

## Amazon S3 

Class AWSCloudStorage provides sample implementation for instantiating Amazon S3 service. 

## AWS STS (Security Token Service)

AppConfig creates a SessionCredential bean which is created using AWS access key id, access key secret and session token. The same is used in AWSCLoudStorage implementation to instantiate Amazon S3.
