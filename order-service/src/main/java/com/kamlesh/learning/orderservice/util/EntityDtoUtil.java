package com.kamlesh.learning.orderservice.util;

import com.kamlesh.learning.orderservice.dto.*;
import com.kamlesh.learning.orderservice.entity.PurchaseOrder;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static PurchaseOrderResponseDto getPurchaseOrderResponseDto(PurchaseOrder purchaseOrder) {
        PurchaseOrderResponseDto dto = new PurchaseOrderResponseDto();
        BeanUtils.copyProperties(purchaseOrder, dto);
        dto.setOrderId(purchaseOrder.getId());
        return dto;
    }

    public static void setTransactionRequestDto(RequestContext context) {
        TransactionRequestDto dto = new TransactionRequestDto();
        dto.setUserId(context.getPurchaseOrderRequestDto().getUserId());
        dto.setAmount(context.getProductDto().getPrice());
        context.setTransactionRequestDto(dto);
    }

    public static PurchaseOrder getPurchaseOrder(RequestContext context) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setUserId(context.getPurchaseOrderRequestDto().getUserId());
        purchaseOrder.setProductId(context.getPurchaseOrderRequestDto().getProductId());
        purchaseOrder.setAmount(context.getProductDto().getPrice());

        TransactionStatus status = context.getTransactionResponseDto().getStatus();
        OrderStatus orderStatus = TransactionStatus.APPROVED.equals(status) ? OrderStatus.COMPLETED : OrderStatus.FAILED;

        purchaseOrder.setStatus(orderStatus);
        return purchaseOrder;
    }
}
