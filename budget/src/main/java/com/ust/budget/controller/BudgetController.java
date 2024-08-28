package com.ust.budget.controller;


import com.ust.budget.entity.Budget;
import com.ust.budget.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    // Create a new Budget
    @PostMapping("/create")
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        Budget createdBudget = budgetService.createBudget(budget);
        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
    }

    // Get all Budgets
    @GetMapping()
    public ResponseEntity<List<Budget>> getAllBudgets() {
        List<Budget> budgets = budgetService.getAllBudgets();
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    // Get a Budget by ID
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        Optional<Budget> budget = budgetService.getBudgetById(id);
        return budget.map(b -> new ResponseEntity<>(b, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get a Budget by Category (within valid date range)
    @GetMapping("/category/{category}")
    public ResponseEntity<Budget> getBudgetByCategory(@PathVariable String category) {
        Optional<Budget> budget = budgetService.getBudgetByCategory(category);
        return budget.map(b -> new ResponseEntity<>(b, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a Budget
    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget updatedBudget) {
        Optional<Budget> budget = budgetService.updateBudget(id, updatedBudget);
        return budget.map(b -> new ResponseEntity<>(b, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete a Budget
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        boolean deleted = budgetService.deleteBudget(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

















//----------------------------------------------------------------------------------------
//package com.example.budgetservice.controller;
//
//import com.example.budgetservice.model.Budget;
//import com.example.budgetservice.service.BudgetService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//        import java.util.List;
//
//@RestController
//@RequestMapping("/budgets")
//public class BudgetController {
//
//    @Autowired
//    private BudgetService budgetService;
//
//    @PostMapping("/create")
//    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
//        Budget createdBudget = budgetService.createBudget(budget);
//        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget updatedBudget) {
//        Budget budget = budgetService.updateBudget(id, updatedBudget);
//        if (budget != null) {
//            return new ResponseEntity<>(budget, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping("/user")
//    public ResponseEntity<List<Budget>> getBudgetsByUserId() {
//        List<Budget> budgets = budgetService.getBudgetsByUserId();
//        return new ResponseEntity<>(budgets, HttpStatus.OK);
//    }
//
//    @GetMapping("/category/{category}")
//    public ResponseEntity<List<Budget>> getBudgetsByCategoryAndUserId(@PathVariable String category) {
//        List<Budget> budgets = budgetService.getBudgetsByCategoryAndUserId(category);
//        return new ResponseEntity<>(budgets, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
//        budgetService.deleteBudget(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    // Admin-specific endpoints
//    @GetMapping("/admin/all")
//    public ResponseEntity<List<Budget>> getAllBudgets() {
//        List<Budget> budgets = budgetService.getAllBudgets();
//        return new ResponseEntity<>(budgets, HttpStatus.OK);
//    }
//}

