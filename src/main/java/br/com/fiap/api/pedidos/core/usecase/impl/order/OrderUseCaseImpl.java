package br.com.fiap.api.pedidos.core.usecase.impl.order;

import br.com.fiap.api.pedidos.core.Client;
import br.com.fiap.api.pedidos.core.Order;
import br.com.fiap.api.pedidos.core.Product;
import br.com.fiap.api.pedidos.core.exception.ClientNotFoundException;
import br.com.fiap.api.pedidos.core.usecase.OrderUseCase;
import br.com.fiap.api.pedidos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.CreateOrderRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.BaseResponse;
import br.com.fiap.api.pedidos.entrypoint.controller.dto.response.OrderResponse;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ClientRepository;
import br.com.fiap.api.pedidos.core.dataprovider.repository.OrderRepository;
import br.com.fiap.api.pedidos.core.dataprovider.repository.ProductRepository;
import br.com.fiap.api.pedidos.dataprovider.repository.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepositoryPort;
    private final ClientRepository clientRepositoryPort;


    public OrderUseCaseImpl(
            OrderRepository orderRepository,
            ProductRepository productRepositoryPort,
            ClientRepository clientRepositoryPort) {
        this.orderRepository = orderRepository;
        this.productRepositoryPort = productRepositoryPort;
        this.clientRepositoryPort = clientRepositoryPort;
    }


    @Override
    public List<Order> getAllOrders() {
             return  orderRepository.getAll();
    }

    @Override
    public Optional<Order> getOrderById(UUID id) {
        return Optional.ofNullable(orderRepository.getById(id));
    }

    @Override
    public Optional<Order> getStatusById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Order saveOrder(Order order) {
        List<Product> products = productRepositoryPort.getAllById(order.getOrderProductIds());//request.orderProductIds());
        Optional<Client> client = clientRepositoryPort.identifyClientByCpf(order.getClient().getClientCpf());

        order.setOrderPrice(calculateOrderPrice(products));
        order.setOrderProducts(products);
        order.setClient(client.get());

        return  orderRepository.save(order);
    }

    @Override
    public void updateOrder(OrderStatusEnum status, UUID id) {
       orderRepository.updateByOrderStatusAndOrderId(status,id);

    }

    @Override
    public void deleteOrder(UUID id) {
        orderRepository.delete(id);
    }

    private Double calculateOrderPrice(List<Product> products) {
        return products.stream()
                .mapToDouble(product -> product.getPrice().doubleValue())
                .sum();
    }
}
