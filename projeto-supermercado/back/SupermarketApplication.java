@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @PostMapping
    public ResponseEntity<String> finalizarCompra(@RequestBody CompraDTO compra) {
        return ResponseEntity.ok("Compra registrada com sucesso! Valor total: R$ " + compra.getTotal());
    }
}
