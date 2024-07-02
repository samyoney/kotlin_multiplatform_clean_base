//
//  ObservableViewModel.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/03.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine
import Shared
import SwiftUI

class SharedViewModel<VM : ViewModel> : ObservableObject, ViewModelStoreOwner {
    var viewModelStore: ViewModelStore = ViewModelStore()
    
    private let key: String = String(describing: type(of: VM.self))
    
    @LazyKoin private var viewModel: VM
    
    @Published private var uiState : AnyObject?
    
    private lazy var stateFlow = {
        guard let viewModel = self.viewModel as? BaseViewModel<AnyObject, AnyObject> else {
            fatalError("View Model is not valid")
        }
        return NativeStateFlow<AnyObject>(flow: viewModel.uiState)
    }()
    
    init() {
        viewModelStore.put(key: key, viewModel: viewModel)
        stateFlow.subscribe(scope: viewModel.viewModelScope) { state in
            if let uiState = state {
                self.uiState = uiState
            }
        }
    }
    
    func getUIState<T>() -> T? {
        return uiState as? T
    }

    var shared: VM {
        get {
            return viewModelStore.get(key: key) as! VM
        }
    }
    
    deinit {
        viewModelStore.clear()
    }
}



