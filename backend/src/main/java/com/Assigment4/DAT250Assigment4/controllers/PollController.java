package com.Assigment4.DAT250Assigment4.controllers;

import com.Assigment4.DAT250Assigment4.PollManager;
import com.Assigment4.DAT250Assigment4.model.Poll;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/polls")
@CrossOrigin
@Tag(name = "Polls", description = "Poll management APIs") // Step 6: API Documentation
public class PollController {

    @Autowired
    private PollManager pollManager;

    @Operation(summary = "Get all polls", description = "Returns a list of all polls") // Step 6: API Documentation
    @GetMapping
    public List<Poll> getAllPolls() {
        return pollManager.getAllPolls();
    }

    @Operation(summary = "Create a new poll", description = "Creates a new poll and returns it") // Step 6: API Documentation
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return pollManager.createPoll(poll);
    }

    @Operation(summary = "Delete a poll", description = "Deletes a poll by its ID") // Step 6: API Documentation
    @DeleteMapping("/{id}")
    public void deletePoll(@PathVariable String id) {
        pollManager.deletePoll(id);
    }
}
