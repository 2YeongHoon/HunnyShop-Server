![Java](https://img.shields.io/badge/Java-11-red.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.6-green.svg)
![JPA](https://img.shields.io/badge/Jpa-2.7.6-green.svg)
![Hibernate](https://img.shields.io/badge/Hibernate-5.6.14-orange.svg)
# HunnyShop-Server
회원, 상품, 주문 도메인을 가진 HunnyShop Site.

# Branch
기본적으로 git-flow 전략을 따릅니다.
```
- `master`: 기준이 되는 브랜치로 제품을 배포하는 브랜치 입니다.

- `develop`: 개발 브랜치로 개발자들이 이 브랜치를 기준으로 각자 작업한 기능들을 합칩니다(Merge).

- `feature/`: 단위 기능을 개발하는 브랜치로 기능 개발이 완료되면 "develop" 브랜치에 합칩니다.
  - `${taskNum}`: 네이밍 규칙은 깃허브의 이슈번호로 생성합니다. 기능 개발 완료 후 "develop" 브랜치에 병합 후 제거됩니다.
```
# Commit Rules
```
NEW: 신규 파일 및 기능 추가
IMP: 기능 개선
SCH: 설정 변경
CLN: 코드 정리 및 리팩토링
BUG: 버그 픽스
TST: 테스트
DOC: 문서작업
RMV: 삭제
```
