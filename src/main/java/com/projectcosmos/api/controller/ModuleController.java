package com.projectcosmos.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectcosmos.api.config.Helpers;
import com.projectcosmos.api.dto.Module.ModuleAttributesDTO;
import com.projectcosmos.api.dto.Module.OuterModuleCreateDTO;
import com.projectcosmos.api.repository.ModuleRepository;

import jakarta.validation.Valid;

import com.projectcosmos.api.entity.Module;

import lombok.RequiredArgsConstructor;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleRepository moduleRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllModules() {
        List<Module> modules = moduleRepository.findAll();

        return helpers.createResponseEntity(true, modules, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getModuleById(@PathVariable Integer id) {
        Module module = moduleRepository.findById(id).orElse(null);

        if (module == null) {
            return helpers.createResponseEntity(false, null, "Module not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, module, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createModule(@RequestBody @Valid OuterModuleCreateDTO module) {
        Module newModule = new Module();
        newModule.setModuleName(module.getModuleName());
        newModule.setIsActive(module.getIsActive());
        newModule.setUsesAmmo(module.getUsesAmmo());
        newModule.setCpu(module.getCpu());
        newModule.setPowerGrid(module.getPowerGrid());
        newModule.setAmmoSubtypeId(module.getAmmoSubTypeId());
        newModule.setSlotType(module.getSlotType());

        newModule.setAttributes(
            new ObjectMapper().convertValue(module.getAttributes(), new TypeReference<Map<String, Object>>() {})
        );

        Module savedModule = moduleRepository.save(newModule);

        return helpers.createResponseEntity(true, savedModule, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateModule(@PathVariable Integer id, @RequestBody @Valid OuterModuleCreateDTO module) {
        Module existingModule = moduleRepository.findById(id).orElse(null);

        if (existingModule == null) {
            return helpers.createResponseEntity(false, null, "Module not found", HttpStatus.NOT_FOUND);
        }

        System.out.println(existingModule);

        if (module.getModuleName() != null)
            existingModule.setModuleName(module.getModuleName());
        if (module.getIsActive() != null) 
            existingModule.setIsActive(module.getIsActive());
        if (module.getUsesAmmo() != null)
            existingModule.setUsesAmmo(module.getUsesAmmo());
        if (module.getCpu() != null)
            existingModule.setCpu(module.getCpu());
        if (module.getPowerGrid() != null)
            existingModule.setPowerGrid(module.getPowerGrid());
        if (module.getAmmoSubTypeId() != null)
            existingModule.setAmmoSubtypeId(module.getAmmoSubTypeId());
        if (module.getSlotType() != null)
            existingModule.setSlotType(module.getSlotType());

        ModuleAttributesDTO attributes = module.getAttributes();

        if (attributes != null) {
            if (attributes.getBaseDamage() != null)
                existingModule.getAttributes().put("baseDamage", attributes.getBaseDamage());

            if (attributes.getBaseFireRate() != null)
                existingModule.getAttributes().put("baseFireRate", attributes.getBaseFireRate());
        }

        Module updatedModule = moduleRepository.save(existingModule);

        return helpers.createResponseEntity(true, updatedModule, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteModule(@PathVariable Integer id) {
        Module module = moduleRepository.findById(id).orElse(null);

        if (module == null) {
            return helpers.createResponseEntity(false, null, "Module not found", HttpStatus.NOT_FOUND);
        }

        moduleRepository.deleteById(id);
        return helpers.createResponseEntity(true, module, "Module with ID " + id + " deleted successfully", HttpStatus.OK);
    }
}
