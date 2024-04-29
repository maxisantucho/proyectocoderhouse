package com.coderhouse.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.modelos.Compra;
import com.coderhouse.modelos.Comprobante;
import com.coderhouse.modelos.Producto;
import com.coderhouse.modelos.Cliente;
import com.coderhouse.repositorios.ClienteRepository;
import com.coderhouse.repositorios.CompraRepository;
import com.coderhouse.repositorios.ProductoRepository;

@Service
public class CompraService {
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private ProductoService productoService;
	
	public List<Compra> listarCompras() {
		return compraRepository.findAll();
	}
	
	public Compra mostrarCompraPorId(int id) {
		return compraRepository.findById(id).orElse(null);
	}
	
	public Compra agregarCompra(Compra compra) throws Exception {
		Cliente cliente = clienteRepository.findById(compra.getCliente().getId()).orElseThrow(IllegalArgumentException::new);
		compra.setCliente(cliente);
		List<Producto> productosLista = new ArrayList<>();
		Comprobante comprobante = new Comprobante();
		double totalComp = 0;
		for(Producto productos : compra.getCliente().getProductos()) {
			productos = productoRepository.findById(productos.getId()).orElseThrow(IllegalArgumentException::new);
			productosLista.add(productos);
			int cantidad = productos.getCantidad();
			double precio = productos.getPrecio();
			double subTotal = precio * cantidad;
			productoService.restarStockProducto(productos.getId(), cantidad);
			totalComp += subTotal;
			compra.setProductos(productosLista);
		}
		compra.setTotalCompra(totalComp);
		comprobante.setTotal(totalComp);
		compra.setComprobante(comprobante);
		crearComprobante(cliente, compra, totalComp);
		
		return compraRepository.save(compra);
	}
	
	public boolean eliminarCompraPorId(int id) {
		try {
			compraRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	public Comprobante crearComprobante(Cliente cliente, Compra compra,
			double total) {
		Comprobante detalleComprobante = new Comprobante();

		detalleComprobante.setCliente(cliente);
		detalleComprobante.setCompra(compra);
		detalleComprobante.setTotal(total);
		return detalleComprobante;
	}

}
