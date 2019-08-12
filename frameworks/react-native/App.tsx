import React from "react";
import {
  TouchableHighlight,
  Button,
  StyleSheet,
  Text,
  View,
  ScrollView
} from "react-native";

import { buildData } from "./util";

class Row extends React.Component<any> {
  onSelect = () => {
    this.props.select(this.props.item);
  };

  onRemove = () => {
    this.props.remove(this.props.item);
  };

  shouldComponentUpdate(nextProps) {
    return (
      nextProps.item !== this.props.item ||
      nextProps.selected !== this.props.selected
    );
  }

  render() {
    let { selected, item } = this.props;
    return (
      <TouchableHighlight onPress={this.onSelect}>
        <View
          onPress={this.onSelect}
          style={selected ? styles.selected : styles.notSelected}
        >
          <Text className="col-md-1">{item.id}</Text>
          <Text>{item.label}</Text>
          <Text onPress={this.onRemove}>X</Text>
          <View className="col-md-6" />
        </View>
      </TouchableHighlight>
    );
  }
}

function MyButton({ id, cb, title }) {
  return (
    <View className="col-sm-6 smallpad">
      <Button
        type="button"
        className="btn btn-primary btn-block"
        id={id}
        onPress={cb}
        title={title}
      />
    </View>
  );
}

class Jumbotron extends React.Component<any> {
  shouldComponentUpdate() {
    return false;
  }

  render() {
    const { run, runLots, add, update, clear, swapRows } = this.props;
    return (
      <View className="jumbotron">
        <View className="row">
          <View className="col-md-6">
            <Text styles={styles.h1}>React Native</Text>
          </View>
          <View className="col-md-6">
            <View className="row">
              <MyButton id="run" title="Create 1,000 rows" cb={run} />
              <MyButton id="runlots" title="Create 10,000 rows" cb={runLots} />
              <MyButton id="add" title="Append 1,000 rows" cb={add} />
              <MyButton id="update" title="Update every 10th row" cb={update} />
              <MyButton id="clear" title="Clear" cb={clear} />
              <MyButton id="swaprows" title="Swap Rows" cb={swapRows} />
            </View>
          </View>
        </View>
      </View>
    );
  }
}

class Main extends React.Component<any> {
  state = {
    data: buildData(10),
    selected: 3
  };

  run = () => {
    this.setState({ data: buildData(1000), selected: 0 });
  };

  runLots = () => {
    this.setState({ data: buildData(10000), selected: 0 });
  };

  add = () => {
    this.setState({
      data: this.state.data.concat(buildData(1000)),
      selected: this.state.selected
    });
  };

  update = () => {
    const data = this.state.data;
    for (let i = 0; i < data.length; i += 10) {
      const item = data[i];
      data[i] = { id: item.id, label: item.label + " !!!" };
    }
    this.forceUpdate();
  };

  select = item => {
    this.setState({ selected: item.id });
  };

  remove = item => {
    const data = this.state.data;
    data.splice(data.indexOf(item), 1);
    this.forceUpdate();
  };

  clear = () => {
    this.setState({ data: [], selected: 0 });
  };

  swapRows = () => {
    const data = this.state.data;
    if (data.length > 998) {
      let temp = data[1];
      data[1] = data[998];
      data[998] = temp;
    }
    this.forceUpdate();
  };

  render() {
    return (
      <View className="container">
        <Jumbotron
          run={this.run}
          runLots={this.runLots}
          add={this.add}
          update={this.update}
          clear={this.clear}
          swapRows={this.swapRows}
        />
        <ScrollView className="table table-hover table-striped test-data">
          {this.state.data.map(item => (
            <Row
              key={item.id}
              item={item}
              selected={this.state.selected === item.id}
              select={this.select}
              remove={this.remove}
            />
          ))}
        </ScrollView>
        <Text
          className="preloadicon glyphicon glyphicon-remove"
          aria-hidden="true"
        >
          x
        </Text>
      </View>
    );
  }
}

export default function App() {
  return <Main />;
}

const commonRowStyle = {
  flexDirection: "row",
  alignItems: "center"
} as const;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center"
  },
  h1: {
    fontSize: 12
  },
  selected: {
    ...commonRowStyle,
    backgroundColor: "red"
  },
  notSelected: {
    ...commonRowStyle
  }
});
