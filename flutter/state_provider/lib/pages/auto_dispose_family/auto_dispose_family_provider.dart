import 'package:flutter_riverpod/flutter_riverpod.dart';

final autoDisposeFamilyCounterProvider = StateProvider.autoDispose.family((
  Ref ref,
  int initialValue,
) {
  ref.onDispose(() {
    print('[familyCounterAutoDisposeFamily] onDispose');
  });
  return initialValue;
});
