<h1 align="center"> 
    URL Shortener
</h1>
<p align="center">
    Spring Boot URL Shortener with AWS Deployment
</p>

This project was an opportunity to learn both Spring Boot and AWS.  
For those who want a deeper dive into the deployment process, architecture choices, and lessons learned, I wrote a complete article:  
➡️ [Deploying an URL Shortener on AWS](https://davisiqueira1.github.io/blog/deploying-an-url-shortener-on-aws)

## Built with

![Spring]
![AWS]
![JavaScript]
![MongoDB]

## Prerequisites

- [Java](https://www.oracle.com/br/java/technologies/downloads/) (17+)
- [Maven](https://maven.apache.org/download.cgi)
- [MongoDB](https://www.mongodb.com/try/download/community)

## Setup environment (Local Development)

1. **Clone the repository**:

```bash
git clone https://github.com/davisiqueira1/url-shortener.git url-shortener
```

2. **Configure the Backend**:
   - Navigate to the backend directory
   - Update `application.properties`

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/urlshortener
server.port=8080
cors.allowed-origins=*
spring.data.mongodb.auto-index-creation=true
```

3. **Run the Backend**:

```bash
cd backend
mvn spring-boot:run
```

4. Access the application locally:

```
Frontend: simply open index.html in your browser
Backend API: http://localhost:8080
```

## AWS Deployment

The main focus of this project was deploying the application to AWS with a production-grade architecture.  
Below is a summary of what was implemented in AWS:

### **Architecture**

- **Backend**:
  - Hosted on **EC2** (Ubuntu) behind an **Application Load Balancer (ALB)**
  - **ACM** SSL Certificate for HTTPS
  - **Route 53** DNS for custom domain (`api.davisiqueira.com`)
- **Frontend**:
  - Hosted on **S3** (private bucket) with **CloudFront** as CDN
  - HTTPS via ACM in **us-east-1**
  - Default root object configured for SPA support
- **Database**:
  - **MongoDB Atlas** (cloud-hosted)

**Diagram:**

```
[Client] -> [CloudFront + ACM] -> [S3 Bucket (Frontend)]
       |
       -> [Route 53 DNS] -> [ALB + ACM] -> [EC2 Backend] -> [MongoDB Atlas]
```

### **Deployment Flow**

1. **Frontend**:
   - Uploaded to **S3**
   - Served via **CloudFront** (HTTPS enforced)
2. **Backend**:
   - Packaged with Maven (`mvn package`)
   - JAR deployed to EC2
   - ALB handles HTTPS and redirects HTTP → HTTPS
3. **Domain**:
   - Managed with **Route 53**
   - CNAME and Alias records for CloudFront and ALB
4. **Certificates**:
   - Issued and validated through **AWS Certificate Manager (ACM)**

[Spring]: https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white
[AWS]: https://img.shields.io/badge/AWS-FF9900?style=for-the-badge&logo=amazonwebservices&logoColor=white
[JavaScript]: https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black
[MongoDB]: https://img.shields.io/badge/-MongoDB-13aa52?style=for-the-badge&logo=mongodb&logoColor=white
