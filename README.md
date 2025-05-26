# Ramos Sample MSA
- 라모스의 GitOps 적용을 위한 MSA Demo Application
- Spring Cloud 기반 마이크로서비스 예제에 해당함.
  - Service Discovery
  - Gateway
  - Book Service
  - Author Service
  - Review Service
- 각 마이크로서비스는 예제를 단순화하기 위해 CQRS 패턴에서의 조회 API(Query)로만 구성함.
- 각 서비스의 DB는 In-Memory 기반의 H2 Database에 샘플 데이터를 구성하였고, 향후 디테일한 아키텍처로는 내부적으로 MySQL 혹은 PostgreSQL 등을 구성하고 K8s Cluster 내 다른 namespace에 구성된 독자적인 바운더리 컨텍스트를 갖고 있는 마이크로서비스(리뷰 서비스, 저자 서비스 등)에서 Command(등록, 수정, 삭제 이벤트)가 발생하면 Kafka Connect와 같은 CDC(Change Data Capture) 파이프라인을 구성해 동기화 된 데이터를 기반으로 API의 응답 결과를 사용자에게 제공한다 가정합니다.
- Book Service는 비즈니스 로직 상 외부 서버인 Author Service와 Review Service를 REST API로 호출해야 하는데 각 마이크로서비스는 독립적으로 배포되기 때문에 배포 중 순간적으로 장애 상황이 일어날 수 있어 Circuit Breaker를 적용하여 더미 데이터로 구성된 Fallback 응답을 내려주는 방식으로 사용자에겐 장애 상황에 대한 여파를 최소화 할 수 있도록 적용함.
- author: HakHyeon Song
- 2025.04 ~ 2025.05

![image](https://github.com/user-attachments/assets/c4f5adce-fbe5-48a4-839f-2e1ec44d68f3)

## Tech Stack
- Java 17 / Spring Boot
- Spring Cloud
  - Eureka Server / Client
  - Spring Cloud Gateway
  - Open Feign
  - Circuit Breaker (Resilience4J)

### 참고 사항
- 로컬 환경에서 실행 시 `-Dspring.profiles.active=loc`으로 지정하여 실행.
