//
//  SharedViewModel.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/06/28.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

class SharedViewModelStoreOwner<VM : ViewModel> : ObservableObject, ViewModelStoreOwner {
    var viewModelStore: ViewModelStore = ViewModelStore()
    
    private let key: String = String(describing: type(of: VM.self))
    
    init(_ viewModel: VM = .init()) {
        viewModelStore.put(key: key, viewModel: viewModel)
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

