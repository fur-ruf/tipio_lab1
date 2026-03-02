const { getGraphResult, getErrorObject } = require('./complexObjects');
require('dotenv').config({ path: '.env.test' });

test('хрупкий тест для графа', () => {
  const result = getGraphResult();
  expect(result).toMatchSnapshot();
});

test('хрупкий тест для объекта с ошибкой', () => {
  const error = getErrorObject();
  expect(error).toMatchSnapshot();
});


test('игнорим нестабильные поля графа', () => {
  const result = getGraphResult();
  
  expect(result).toMatchSnapshot({
    visitedAt: expect.any(String),    
    sessionId: expect.any(String),      
    metadata: {
      createdAt: expect.any(String),   
      environment: expect.any(String)
    }
  });
});

test('игнорим нестабильныее поля объекта с ошибкой', () => {
  const error = getErrorObject();
  
  expect(error).toMatchSnapshot({
    timestamp: expect.any(Number),      
    requestId: expect.any(String),       
    hash: expect.any(String),
    stack: expect.any(String),            
    performance: {
      duration: expect.any(Number),      
      memory: expect.any(Number)        
    }
  });
});


test('чек влияния измений для графа', () => {
  const result = getGraphResult(true);  // false для первого snapshot-а, потом тру и ловим изменения
  
  expect(result).toMatchSnapshot({
    visitedAt: expect.any(String),
    sessionId: expect.any(String),
    metadata: {
      createdAt: expect.any(String), 
      environment: expect.any(String)
    }
  });
});

test('чек влияния изменения для объекта с ошибкой', () => {
  const error = getErrorObject(true); // false для первого snapshot-а, потом тру и ловим изменения
  
  expect(error).toMatchSnapshot({
    timestamp: expect.any(Number),      
    requestId: expect.any(String),       
    hash: expect.any(String),
    stack: expect.any(String),            
    performance: {
      duration: expect.any(Number),      
      memory: expect.any(Number)        
    }
  });
});