import 'package:freezed_annotation/freezed_annotation.dart';

part 'person.freezed.dart';

@freezed
sealed class Person with _$Person {
  factory Person({
    required int id,
    required String name,
    required String email,
  }) = _Person;
}
