import type {PropsWithChildren} from 'react';
import type {StyleProp, ViewStyle} from 'react-native';
import {requireNativeComponent} from 'react-native';

type RNDListViewProps = PropsWithChildren<{
  style: StyleProp<ViewStyle>;
  size: number
  onNextBatch: () => void
}>;

export const RNDListView =
  requireNativeComponent<RNDListViewProps>('RNDListView');

const RNDListViewItem =
  requireNativeComponent<CustomNativeRecyclerItemViewInterface>(
    'RNDListViewItem',
  );

type CustomNativeRecyclerItemViewInterface = PropsWithChildren<{
  style: StyleProp<ViewStyle>;
}>;
