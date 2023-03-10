/* eslint-disable react-native/no-inline-styles */
import React, {useRef, useState} from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Text,
  View,
  FlatList,
  requireNativeComponent,
  ViewStyle,
  StyleProp,
} from 'react-native';

import {RecyclerView} from './RecyclerViewList';
import DataSource from './DataSource';

const DATA = Array(500)
  .fill(null)
  .map((_, idx) => ({
    key: idx.toString(),
    text: `Card #${idx}`,
  }));

const List = () => {
  //const [dataSource] = useState(new DataSource(DATA, x => x.key));
  //const ref = useRef<RecyclerView | null>(null);

  const shouldUseFlatList = false;

  if (shouldUseFlatList) {
    return (
      <View style={[styles.flex, styles.center, styles.border]}>
        <Text>Flat List!</Text>
        <FlatList
          disableVirtualization={false}
          data={DATA}
          renderItem={({item, index}) => {
            return (
              <View style={[styles.border, styles.card, styles.center]}>
                <Text style={styles.text}>{item.text}</Text>
                <Text style={styles.text}>{item.text}</Text>
                <Text style={styles.text}>{item.text}</Text>
                <Text style={styles.text}>{item.text}</Text>
                <Text style={styles.text}>{item.text}</Text>
              </View>
            );
          }}
        />
      </View>
    );
  }

  return (
    <View style={[styles.flex, styles.center, styles.border]}>
      <Text>Custom List!</Text>
      <CustomNativeRecyclerView style={{flex: 1, backgroundColor: 'yellow'}}>
        {Array.from({length: 500}).map((_item, i) => (
          <CustomNativeRecyclerViewItem
            style={{height:100, width: 300, backgroundColor: 'pink'}}>
            <Text style={styles.text}>{DATA[i].text}</Text>
            <Text style={styles.text}>{DATA[i].text}</Text>
            <Text style={styles.text}>{DATA[i].text}</Text>
            <Text style={styles.text}>{DATA[i].text}</Text>
            <Text style={styles.text}>{DATA[i].text}</Text>
          </CustomNativeRecyclerViewItem>
        ))}
      </CustomNativeRecyclerView>

      {/* <RecyclerView 
        ref={ref}
        style={[styles.list, styles.border]}
        dataSource={dataSource}
        renderItem={({item}, index) => {
          return (
            <View style={[styles.border, styles.card, styles.center]}>
              <Text style={styles.text}>{item.text}</Text>
              <Text style={styles.text}>{item.text}</Text>
              <Text style={styles.text}>{item.text}</Text>
              <Text style={styles.text}>{item.text}</Text>
              <Text style={styles.text}>{item.text}</Text>
            </View>
          );
        }}
      /> */}
    </View>
  );
};
const App = () => {
  return (
    <SafeAreaView style={styles.flex}>
      <List />
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  flex: {
    flex: 1,
  },
  center: {
    alignItems: 'center',
    justifyContent: 'center',
  },
  card: {
    width: 300,
    height: 200,
    marginVertical: 8,
    backgroundColor: 'yellow',
  },
  border: {
    borderColor: 'aqua',
    borderWidth: 4,
  },
  list: {
    flex: 1,
    width: 500,
    backgroundColor: 'white',
    // alignItems: 'stretch',
    // justifyContent: 'flex-start',
  },
  text: {
    color: 'black',
  },
});

export default App;

const CustomNativeRecyclerView =
  requireNativeComponent<CustomNativeRecyclerViewInterface>('RCTRecycleview');

const CustomNativeRecyclerViewItem =
  requireNativeComponent<CustomNativeRecyclerItemViewInterface>(
    'RCTRecycleviewItemview',
  );

export interface CustomNativeRecyclerViewInterface {
  style: StyleProp<ViewStyle>;
}

export interface CustomNativeRecyclerItemViewInterface {
  style: StyleProp<ViewStyle>;
}
