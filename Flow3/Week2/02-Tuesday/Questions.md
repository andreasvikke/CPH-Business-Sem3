# Questions Thuesday
---
Q: **What is the purpose of the key value, which must be given to individual rows in a react-list**
A: To have a unique identifyer for each item, so when the item gets updated the virtual DOM can change the single item instead of remaking the whole DOM.
---
Q: **It's recommended to use a unique value from your data if available (like an ID). How do you get a unique value in a map callback, for data without a unique id?**
A: In the map function there's 4 build in variables you can use, the second one is the index in the array. The index can be used as the key, as the index is always unique.
---
Q: **What is the difference(s) between state and props?**
A: State is a datastructure and will be instansiated with a defualt value when mounted. The props short for properties are componets arguments, they are sent from above and can both be data and callbacks.
---
Q: **For which scenarios would you use props, and for which would you use state?**
A: Props will be used to pass properties down to a component. State will be used to save properties in the state, so we can update the data and therefore the DOM
---
Q: **Where is the only place you can set state directly as in:  this.state = {name: "Peter"};**
A: Inside the constructor of a class
---
Q: **How must you set state all other places?**
A: With the useState hook. ([arg, setArg] = usestate(val))
---