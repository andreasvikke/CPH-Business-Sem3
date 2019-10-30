import React from 'react';
import PropTypes from 'prop-types';

const TodoList = ({ todos }) => {
  return (
    <React.Fragment>
      <h2>All Todos</h2>
      <ul>
        {todos.map(todo => (
          <li key={todo.id}>{todo.todoText}</li>
        ))}
      </ul>
    </React.Fragment>
  );
};
export default TodoList;

TodoList.propTypes = {
  todos: PropTypes.array
}
