# Apuntes
### SPRING BOOT

* Controlador -> @RestController
```java
@RequestMapping(value="/usuarios", method= RequestMethod.GET) or @GetMapping("usuarios")
Paramentros -> @RequestParam(value="name", defaultValue="World") String nameç
```
@RequestBody -> Permite inyectar el cuerpo de la perticon en un objeto
@PathVariable -> Nos permite inyectar un fragmento de la URL en una variable

ModelMapper-> añadir dependencia modelmapper
Creamos un bean
```java 
@Bean
public ModelMapper modelMapper(){
return new ModelMapper();
}
```
```java 
@Component @RequiredArgsConstructor
public class ProductoDTOConverter{
	private final ModelMapper modelMapper;
	public ProductoDTO convertToDto(Producto producto){
		return modelMapper.map(producto, ProductoDTO.class)
	}
}
```




### Errores:
para fechas formato json anotacion -> @JsonFormat(shape= Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")

@ExceptionHandler -> Unificar excepciones y personalizar mensaje
@ControllerAdvice

### Documentar Springboot Swagger:
Swagger + SpringFox
2 dependencias -> springfox-swagger2 y springfox-swagger-ui

@EnableSwagger2 en una clase @Configuration

```java 
@Bean 
public Docket api(){
	return new DSocker(DomentationType.SWAGGER_2)
.selects()
.api(ReqiestJamdlerSelectors.basePackage("paquete de controladores com.dfsd.ss.controllers"))
.paths(PathSelectors.any())
.build();
}

```







