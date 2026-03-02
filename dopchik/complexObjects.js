function getGraphResult(showNewField = false) {
    const result = {
      root: "A",
      nodes: [
        { id: "1", name: "A", friends: ["B", "C"] },
        { id: "2", name: "B", friends: ["A", "D"] },
        { id: "3", name: "C", friends: ["A", "E"] },
      ],
      visitedAt: new Date().toISOString(),
      sessionId: Math.random().toString(36).substring(2, 10),
      version: "1.0.0",
      metadata: {
        createdAt: new Date().toISOString(),
        environment: process.env.NODE_ENV 
      }
    };
    
    if (showNewField) {
      result.description = "Blablabla";
      result.priority = "high";
      result.tags = ["user", "admin", "test"];
    }
    
    return result;
  }
  
  function getErrorObject(showNewField = false) {
    let error;
    try {
      throw new Error("What the problem, broo?");
    } catch (e) {
      error = e;
    }
  
    const result = {
      name: error.name,
      message: error.message,
      stack: error.stack,
      timestamp: Date.now(),
      requestId: Math.random().toString(36).substring(7),
      userId: "user_123",
      hash: Math.random().toString(36).substring(2, 15),
      performance: {
        duration: Math.random() * 1000,
        memory: Math.random() * 1000000
      }
    };
    
    if (showNewField) {
      result.statusCode = 500;
      result.details = {
        service: "user-service",
        retryable: true
      };
      result.severity = "high";
    }
    
    return result;
  }
  
  module.exports = { getGraphResult, getErrorObject };