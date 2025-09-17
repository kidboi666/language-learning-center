// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'family_provider.dart';

// **************************************************************************
// RiverpodGenerator
// **************************************************************************

// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: type=lint, type=warning

@ProviderFor(familyHello)
const familyHelloProvider = FamilyHelloFamily._();

final class FamilyHelloProvider
    extends $FunctionalProvider<String, String, String>
    with $Provider<String> {
  const FamilyHelloProvider._({
    required FamilyHelloFamily super.from,
    required String super.argument,
  }) : super(
         retry: null,
         name: r'familyHelloProvider',
         isAutoDispose: true,
         dependencies: null,
         $allTransitiveDependencies: null,
       );

  @override
  String debugGetCreateSourceHash() => _$familyHelloHash();

  @override
  String toString() {
    return r'familyHelloProvider'
        ''
        '($argument)';
  }

  @$internal
  @override
  $ProviderElement<String> $createElement($ProviderPointer pointer) =>
      $ProviderElement(pointer);

  @override
  String create(Ref ref) {
    final argument = this.argument as String;
    return familyHello(ref, argument);
  }

  /// {@macro riverpod.override_with_value}
  Override overrideWithValue(String value) {
    return $ProviderOverride(
      origin: this,
      providerOverride: $SyncValueProvider<String>(value),
    );
  }

  @override
  bool operator ==(Object other) {
    return other is FamilyHelloProvider && other.argument == argument;
  }

  @override
  int get hashCode {
    return argument.hashCode;
  }
}

String _$familyHelloHash() => r'9fb011f1cd88523341d6ee88ca6ecde279e33a37';

final class FamilyHelloFamily extends $Family
    with $FunctionalFamilyOverride<String, String> {
  const FamilyHelloFamily._()
    : super(
        retry: null,
        name: r'familyHelloProvider',
        dependencies: null,
        $allTransitiveDependencies: null,
        isAutoDispose: true,
      );

  FamilyHelloProvider call(String name) =>
      FamilyHelloProvider._(argument: name, from: this);

  @override
  String toString() => r'familyHelloProvider';
}
