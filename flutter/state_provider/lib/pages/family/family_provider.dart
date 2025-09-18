
import 'package:flutter_riverpod/flutter_riverpod.dart';

final familyCounterProvider = StateProvider.family((Ref ref, int initialValue) {
  ref.onDispose(() {
    print('[familyCounterProvider] onDispose');
  });
  return initialValue;
});