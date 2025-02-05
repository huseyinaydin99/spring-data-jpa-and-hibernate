package tr.com.huseyinaydin.orderservice.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import tr.com.huseyinaydin.orderservice.domain.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByDescription(String description);

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findById(Long aLong);
    /*
    Pessimistic Locking (Karamsar Kilit), veriye erişen her işlem önceden kilitlenerek diğer işlemlerin aynı veriyi okumasını ya da değiştirmesini engeller, böylece çakışma riskini tamamen ortadan kaldırır.

    Örnek olarak, bir bankada hesap bakiyesi güncelleme işlemi üzerinde pessimistic locking kullanıldığını düşünelim:

    İşlem 1: Bir kullanıcı, banka hesabındaki bakiye üzerinde işlem yapmaya başlar ve sistem bu kaydı kilitler (yani, pessimistic lock uygular).
    İşlem 2: Aynı anda başka bir kullanıcı aynı hesabı güncellemeye çalışır, ancak kilit nedeniyle işlem 1 bitene kadar beklemek zorundadır.
    İşlem 1 tamamlandığında kilit açılır ve işlem 2 kaydın kilidi çözüldüğü için kendi işlemini gerçekleştirebilir.
    Bu yöntem, çakışma olmaması için verinin sürekli olarak kilitlenmesi anlamına gelir ve yalnızca bir işlem veriye erişebilir.

    Commit: Bir işlem başarıyla tamamlandığında, yapılan tüm değişiklikler kalıcı hale gelir ve veritabanına kaydedilir.
    Rollback: Bir işlem sırasında bir hata meydana geldiğinde, yapılan tüm değişiklikler geri alınır ve veritabanı önceki geçerli durumuna döner.
    Savepoint: Bir işlem içinde belirli bir noktada ara kayıt noktası oluşturur, böylece işlemin sadece o noktadan sonrası geri alınabilir.
    */
}