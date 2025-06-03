# Cache Module

이 모듈은 애플리케이션의 캐싱 기능을 제공합니다.

## 주요 기능

### 1. CacheConfig
- 캐시 매니저 설정
- 캐시 이름 정의
- 캐시 활성화

### 2. CacheUtil
- 캐시 데이터 저장
- 캐시 데이터 조회
- 캐시 데이터 삭제
- 캐시 초기화

## 사용 방법

### 1. 캐시 어노테이션 사용
```java
@Service
public class UserService {
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        // 사용자 조회 로직
    }

    @CachePut(value = "users", key = "#user.id")
    public User updateUser(User user) {
        // 사용자 업데이트 로직
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        // 사용자 삭제 로직
    }
}
```

### 2. CacheUtil 사용
```java
@Service
public class ProductService {
    @Autowired
    private CacheUtil cacheUtil;

    public Product getProduct(Long id) {
        // 캐시에서 조회
        Product product = cacheUtil.get("products", id, Product.class);
        if (product != null) {
            return product;
        }

        // DB에서 조회
        product = productRepository.findById(id).orElse(null);
        if (product != null) {
            // 캐시에 저장
            cacheUtil.put("products", id, product);
        }
        return product;
    }

    public void clearProductCache() {
        cacheUtil.clear("products");
    }
}
```

## 캐시 설정

### 기본 캐시
- users: 사용자 정보 캐시
- products: 상품 정보 캐시
- categories: 카테고리 정보 캐시

## 주의사항

1. 캐시 데이터의 일관성을 유지해야 합니다.
2. 캐시 크기를 적절히 관리해야 합니다.
3. 캐시 만료 정책을 설정해야 합니다.
4. 분산 환경에서는 분산 캐시를 고려해야 합니다.