//
//  BaseSharedVM.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/10.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

protocol BaseSharedVMProtocol: ObservableObject {
    associatedtype State: AnyObject
    associatedtype Event: AnyObject
    
    func createSharedVM() -> BaseViewModel<State, Event>
    func onTriggerEvent(eventType: Event)
    func onNotifyPublisherDidChange(state: State)
}

class BaseSharedVM<State: AnyObject, Event: AnyObject>: BaseSharedVMProtocol {
    
    private var sharedVM: BaseViewModel<State, Event>?

    init() {
        self.sharedVM = createSharedVM()
        sharedVM?.onSharedDataListener { state in
            guard let state = state else { return }
            DispatchQueue.main.async { [weak self] in
                self?.onNotifyPublisherDidChange(state: state)
            }
        }
    }
    
    func createSharedVM() -> BaseViewModel<State, Event> {
        fatalError("Must be overridden by subclass")
    }
    
    @MainActor
    func onNotifyPublisherDidChange(state: State) {
        fatalError("Must be overridden by subclass")
    }
    
    final func onTriggerEvent(eventType: Event) {
        sharedVM?.onTriggerEvent(eventType: eventType)
        if let state = sharedVM?.getSharedData() {
            DispatchQueue.main.async { [weak self] in
                self?.onNotifyPublisherDidChange(state: state)
            }
        }
    }
    
    deinit {
        sharedVM?.onCleared()
        sharedVM = nil
    }
}

