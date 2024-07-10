//
//  Publisher+Extension.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/09.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine

@propertyWrapper
struct SharedPublisher<T, UIState>: Publisher {
    typealias Output = T
    typealias Failure = Never
    
    private var value: T
    
    private var defaultValue: T

    private let subject = PassthroughSubject<T, Never>()

    private let propertyName: String?
    private let generator: () -> UIState?
 
    init(_ generator: @escaping () -> UIState? = { nil }, defaultValue: T, forPropertyName: String = String()) {
        self.propertyName = forPropertyName
        self.generator = generator
        self.value = defaultValue
        self.defaultValue = defaultValue
    }
    
    var projectedValue: AnyPublisher<T, Never> {
        return subject.eraseToAnyPublisher()
    }

    var wrappedValue: T {
        mutating get {
            subject.send(value)
            return value
        }
        set {
            value = newValue
            subject.send(value)
        }
    }

    func receive<S>(subscriber: S) where S : Subscriber, Never == S.Failure, T == S.Input {
        subject.subscribe(subscriber)
    }
    
    private func getValueInsideUIState(forPropertyName propertyName: String) -> T? {
        guard let sharedUIState = generator() else { return nil }
        let mirror = Mirror(reflecting: sharedUIState)
         for child in mirror.children {
             if let label = child.label, label == propertyName {
                 return child.value as? T
             }
         }
         return nil
     }
}
