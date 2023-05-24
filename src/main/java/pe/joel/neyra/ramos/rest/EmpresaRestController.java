package pe.joel.neyra.ramos.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.joel.neyra.ramos.model.Empresa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaRestController {

    public List<Empresa> empresas = new ArrayList<>();

    @GetMapping
    List<Empresa>listar(){
        return empresas;
    }



    @GetMapping("/{idEmpresa}")
    Empresa obtener (@PathVariable Long idEmpresa){
        return empresas
                .stream()
                .filter(c -> c.getIdEmpresa().equals(idEmpresa))
                .findFirst()
                .orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Empresa crear (@RequestBody Empresa empresa){
        empresa.setIdEmpresa(new Random().nextLong(100));
        empresa.setFechaCreacion(LocalDateTime.now());


        empresas.add(empresa);
        return empresa;

    }


    @PutMapping("/{idEmpresa}")
    Empresa actualizar(@PathVariable Long idEmpresa, @RequestBody Empresa empresaForm){

        Empresa empresa = empresas
                .stream()
                .filter(c -> c.getIdEmpresa().equals(idEmpresa))
                .findFirst()
                .orElse(null);
        if (empresa == null)return  null;

        empresa.setRazonSocial(empresaForm.getRazonSocial());
        empresa.setRuc(empresaForm.getRuc());
        empresa.setDireccion(empresaForm.getDireccion());
        empresa.setEstado(empresaForm.getEstado());
        empresa.setFechaActualizacion(LocalDateTime.now());

        return empresa;

    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Long idEmpresa){

        empresas.removeIf(c -> c.getIdEmpresa().equals(idEmpresa));
    }
}
