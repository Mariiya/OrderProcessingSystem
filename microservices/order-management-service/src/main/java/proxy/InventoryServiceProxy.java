package proxy;

import com.mako.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${microservices.inventory-service.name}",
        url = "localhost:8082")
public interface InventoryServiceProxy {

    @PostMapping("/inventory/orders")
    ResponseEntity<OrderDTO> storeOrder(@RequestBody OrderDTO orderDTO);
}
