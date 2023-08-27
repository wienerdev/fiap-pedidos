package br.com.fiap.api.pedidos.domain.usecase;

import br.com.fiap.api.pedidos.domain.Client;
import br.com.fiap.api.pedidos.domain.Order;
import br.com.fiap.api.pedidos.domain.Product;
import br.com.fiap.api.pedidos.domain.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.domain.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.domain.dto.response.OrderResponse;
import br.com.fiap.api.pedidos.domain.port.repository.ClientRepositoryPort;
import br.com.fiap.api.pedidos.domain.port.repository.OrderRepositoryPort;
import br.com.fiap.api.pedidos.domain.port.repository.ProductRepositoryPort;
import br.com.fiap.api.pedidos.domain.port.usecase.OrderUseCasePort;
import br.com.fiap.api.pedidos.infra.adapters.entity.ProductEntity;

import java.util.List;
import java.util.UUID;

public class OrderUseCase implements OrderUseCasePort {

    private final OrderRepositoryPort orderRepository;
    private final ProductRepositoryPort productRepositoryPort;
    private final ClientRepositoryPort clientRepositoryPort;


    public OrderUseCase(
            OrderRepositoryPort orderRepository,
            ProductRepositoryPort productRepositoryPort,
            ClientRepositoryPort clientRepositoryPort) {
        this.orderRepository = orderRepository;
        this.productRepositoryPort = productRepositoryPort;
        this.clientRepositoryPort = clientRepositoryPort;
    }


    @Override
    public BaseResponse<Iterable<OrderResponse>> getAllOrders() {
        return new BaseResponse<>(
                true,
                orderRepository.getAll().stream().map(OrderResponse::fromEntityToResponse).toList());
    }

    @Override
    public BaseResponse<OrderResponse> getOrderById(UUID id) {
        return new BaseResponse<>(
                true,
                OrderResponse.fromEntityToResponse(orderRepository.getById(id)));
    }

    @Override
    public BaseResponse<OrderResponse> saveOrder(CreateOrderRequest request) {
        Order entity = CreateOrderRequest.fromResponseToOrder(request);

        List<Product> products = productRepositoryPort.getAllById(request.orderProductIds());
        Client client = clientRepositoryPort.identifyClientByCpf(request.clientCpf());

        entity.setOrderPrice(calculateOrderPrice(products));
        entity.setOrderProducts(ProductEntity.toProductEntityList(products));
        entity.setClient(client);

        return new BaseResponse<>(
                true,
                OrderResponse.fromEntityToResponse(orderRepository.save(entity)));
    }

    @Override
    public BaseResponse updateOrder(UpdateOrderRequest request) {
        orderRepository.updateByOrderStatusAndOrderId(request.orderStatus(), request.orderId());
        return new BaseResponse<>(
                true,
                null
        );
    }

    @Override
    public BaseResponse deleteOrder(UUID id) {
        orderRepository.delete(id);
        return new BaseResponse(
                true,
                null
        );
    }

    private Double calculateOrderPrice(List<Product> products) {
        return products.stream()
                .mapToDouble(product -> product.getPrice().doubleValue())
                .sum();
    }
}
